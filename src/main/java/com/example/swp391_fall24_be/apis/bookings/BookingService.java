package com.example.swp391_fall24_be.apis.bookings;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.accounts.AccountRoleEnum;
import com.example.swp391_fall24_be.apis.accounts.AccountsRepository;
import com.example.swp391_fall24_be.apis.bookings.DTOs.BookingDTO;
import com.example.swp391_fall24_be.apis.bookings.DTOs.CreateBookingDTO;
import com.example.swp391_fall24_be.apis.bookings.DTOs.PaginateBookingDTO;
import com.example.swp391_fall24_be.apis.bookings.DTOs.UpdateBookingDTO;
import com.example.swp391_fall24_be.apis.notifications.NotificationsRepository;
import com.example.swp391_fall24_be.apis.services.ServiceEntity;
import com.example.swp391_fall24_be.apis.services.ServicesRepository;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ErrorEnum;
import com.example.swp391_fall24_be.core.ErrorReport;
import com.example.swp391_fall24_be.core.ProjectException;
import com.example.swp391_fall24_be.utils.AuthUtils;
import com.example.swp391_fall24_be.utils.TimeUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService extends AbstractService<BookingEntity, String, CreateBookingDTO, UpdateBookingDTO, PaginateBookingDTO> {
    private final BookingRepository bookingRepository;

    private final AccountsRepository accountsRepository;

    private final ServicesRepository servicesRepository;

    private final NotificationsRepository notificationsRepository;

    public BookingService(BookingRepository bookingRepository, AccountsRepository accountsRepository, ServicesRepository servicesRepository, NotificationsRepository notificationsRepository) {
        this.bookingRepository = bookingRepository;
        this.accountsRepository = accountsRepository;
        this.servicesRepository = servicesRepository;
        this.notificationsRepository = notificationsRepository;
    }

    public boolean isBookingBookedAtTheTime(LocalDateTime startTime, LocalTime estimatedTime, AccountEntity veterian){
//        List<BookingEntity> veterianBookingList = bookingRepository.
//                findByVeterianAndStatusEnum(veterian, StatusEnum.CONFIRMED);
        List<BookingEntity> veterianBookingList = bookingRepository.
                findByVeterianAndStatuses(veterian, Arrays.asList(StatusEnum.UNPAID, StatusEnum.CONFIRMED));
//                findByVeterianAndStatusEnumOrStatusEnum(veterian, StatusEnum.UNPAID, StatusEnum.CONFIRMED);

        LocalDateTime endTime = startTime.plusHours(estimatedTime.getHour())
                .plusMinutes(estimatedTime.getMinute());;

        for (BookingEntity booking: veterianBookingList) {
            LocalDateTime bookingStartTime = booking.getStartedAt();
            LocalTime bookingServiceEstimatedTime = booking.getService().getEstimatedTime();
            LocalDateTime bookingEndTime = bookingStartTime.plusHours(bookingServiceEstimatedTime.getHour())
                    .plusMinutes(bookingServiceEstimatedTime.getMinute());
            if ((startTime.isBefore(bookingEndTime) && endTime.isAfter(bookingStartTime))) {
                return true;
            }

        }
        return false;
    }

    @Override
    protected void beforeCreate(BookingEntity bookingEntity) throws ProjectException {
        List<ErrorReport> errorReportList = new ArrayList<>();

        bookingEntity.setCustomer(AuthUtils.getCurrentAccount());

        Optional<ServiceEntity> findServiceResult = servicesRepository.findById(bookingEntity.getService().getId());
        if (findServiceResult.isEmpty()) {
            errorReportList.add(new ErrorReport(
                    "BookingsService_beforeCreate",
                    ErrorEnum.EntityNotFound,
                    "Service with ID " + bookingEntity.getService().getId() + " does not exist."));
        }
        else bookingEntity.setService(findServiceResult.get());

        if (bookingEntity.getVeterian() != null){
            Optional<AccountEntity> findVeterianResult = accountsRepository.findByEmail(bookingEntity.getVeterian().getEmail());
            if (findVeterianResult.isEmpty()) {
                errorReportList.add(new ErrorReport(
                        "BookingsService_beforeCreate",
                        ErrorEnum.EntityNotFound,
                        "Veterinarian with Email " + bookingEntity.getVeterian().getEmail() + " does not exist."));
            }
            else if(findVeterianResult.get().getRole() != AccountRoleEnum.VETERIAN){
                errorReportList.add(new ErrorReport(
                        "BookingsService_beforeCreate",
                        ErrorEnum.EntityNotFound,
                        "Account with Email " + bookingEntity.getVeterian().getEmail() + " is not a veterian."));
            }
            else if (isBookingBookedAtTheTime(bookingEntity.getStartedAt(),bookingEntity.getService().getEstimatedTime(),findVeterianResult.get())){
                errorReportList.add(new ErrorReport(
                        "BookingsService_beforeCreate",
                        ErrorEnum.ValidationError,
                        "Booking with startTime "+ bookingEntity.getStartedAt() + " of veterian " + findVeterianResult.get().getEmail() + " is already booked!"
            ));
            }
            else bookingEntity.setVeterian(findVeterianResult.get());
        }



        if (!errorReportList.isEmpty()) {
            throw new ProjectException(errorReportList);
        }
    }

    @Override
    protected void beforeUpdate(BookingEntity oldEntity, BookingEntity newEntity) throws ProjectException {
        oldEntity.setStatusEnum(newEntity.getStatusEnum());
        oldEntity.setAdditionalInformation(newEntity.getAdditionalInformation());
        oldEntity.setUpdatedAt(newEntity.getUpdatedAt());
    }

//    @Override
//    public BookingEntity cancelBooking(String id, String reason) throws ProjectException {
//        BookingEntity booking = bookingRepository.findById(id).orElseThrow(() ->
//                new EntityNotFoundException("Booking not found with ID: " + id)
//        );
//        // Update the status
//        booking.setStatusEnum(StatusEnum.CANCELLED);
//        booking.setAdditionalInformation(reason);
//        booking.setUpdatedAt(LocalDateTime.now());
//        // Save and return the updated entity
//        return bookingRepository.save(booking);
//

    public BookingEntity changeStatus(String id){
        BookingEntity foundBooking = bookingRepository.findById(id).get();
        if (foundBooking.getVeterian() == null){
            foundBooking.setStatusEnum(StatusEnum.PENDING);
        } else {
            foundBooking.setStatusEnum(StatusEnum.CONFIRMED);
        }
        return bookingRepository.save(foundBooking);
    }

      
    @Override
    public BookingEntity delete(String id) throws ProjectException {
        BookingEntity booking = bookingRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Booking not found with ID: " + id)
        );
        // Update the status
        booking.setStatusEnum(StatusEnum.CANCELED);
        // Save and return the updated entity
        return bookingRepository.save(booking);
    }

    @Transactional
    public BookingEntity assignVeterian(String bookingId, String veterianEmail) throws Exception {
        Optional<AccountEntity> findVeterian = accountsRepository.findByEmail(veterianEmail);
        if(findVeterian.isEmpty()) throw new Error("Cannot find veterian with this email " + veterianEmail);
        AccountEntity veterian = findVeterian.get();
        if (veterian.getRole() != AccountRoleEnum.VETERIAN)
            throw new Error("This email" + veterianEmail +" is not a veterian");


        Optional<BookingEntity> findBooking = bookingRepository.findById(bookingId);
        if(findBooking.isPresent()){
            BookingEntity booking = findBooking.get();

            if (isBookingBookedAtTheTime(booking.getStartedAt(),booking.getService().getEstimatedTime(),veterian)){
                throw new Exception("Booking with startTime "+ booking.getStartedAt() + " of veterian " + veterian.getEmail() + " is already booked!");
            }
            if (booking.getStatusEnum() != StatusEnum.PENDING) throw new Exception("Booking is not in PENDING state");
            booking.setVeterian(veterian);
            booking.setStatusEnum(StatusEnum.CONFIRMED);
            booking = bookingRepository.save(booking);

            // Remove all invitations
            notificationsRepository.deleteAllByBooking(booking);
            return booking;
        }
        throw new Exception("Cannot find booking is not a veterian");
    }

    public List<BookingDTO> getByVeterian(AccountEntity veterian, StatusEnum status){
        BookingEntity findBooking = new BookingEntity();
        AccountEntity findVeterian = new AccountEntity();
        findVeterian.setEmail(veterian.getEmail());
        findBooking.setVeterian(findVeterian);
        findBooking.setStatusEnum(status);

        List<BookingEntity> veterianBookingList = bookingRepository.findAll(Example.of(findBooking));
        if(veterianBookingList.isEmpty()){
            throw new Error("Cannot find booking with this token!");
        }
        List<BookingDTO> bookingDTOList = new ArrayList<>();
        for(BookingEntity booking : veterianBookingList ){
            bookingDTOList.add(booking.toResponseDto());
        }

        return bookingDTOList;
    }

    public List<BookingDTO> getByCustomer(AccountEntity customer, StatusEnum status){
        BookingEntity findBooking = new BookingEntity();
        AccountEntity findCustomer = new AccountEntity();
        findCustomer.setEmail(customer.getEmail());
        findBooking.setCustomer(findCustomer);
        findBooking.setStatusEnum(status);

        List<BookingEntity> veterianBookingList = bookingRepository.findAll(Example.of(findBooking));
        if(veterianBookingList.isEmpty()){
            throw new Error("Cannot find booking with this token!");
        }
        List<BookingDTO> bookingDTOList = new ArrayList<>();
        for(BookingEntity booking : veterianBookingList ){
            bookingDTOList.add(booking.toResponseDto());
        }

        return bookingDTOList;
    }
}
