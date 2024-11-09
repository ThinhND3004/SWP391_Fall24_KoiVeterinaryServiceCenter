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
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Override
    protected void beforeCreate(BookingEntity bookingEntity) throws ProjectException {
        List<ErrorReport> errorReportList = new ArrayList<>();

        bookingEntity.setCustomer(AuthUtils.getCurrentAccount());

        if (bookingEntity.getVeterian() != null){
            Optional<AccountEntity> findVeterianResult = accountsRepository.findById(bookingEntity.getVeterian().getId());
            if (findVeterianResult.isEmpty()) {
                errorReportList.add(new ErrorReport(
                        "BookingsService_beforeCreate",
                        ErrorEnum.EntityNotFound,
                        "Veterinarian with ID " + bookingEntity.getVeterian().getId() + " does not exist."));
            } else if(findVeterianResult.get().getRole() != AccountRoleEnum.VETERIAN){
                errorReportList.add(new ErrorReport(
                        "BookingsService_beforeCreate",
                        ErrorEnum.EntityNotFound,
                        "Account with ID " + bookingEntity.getVeterian().getId() + " is not a veterian."));
            }
            bookingEntity.setVeterian(findVeterianResult.get());
        }


        Optional<ServiceEntity> findServiceResult = servicesRepository.findById(bookingEntity.getService().getId());
        if (findServiceResult.isEmpty()) {
            errorReportList.add(new ErrorReport(
                    "BookingsService_beforeCreate",
                    ErrorEnum.EntityNotFound,
                    "Service with ID " + bookingEntity.getService().getId() + " does not exist."));
        }
        bookingEntity.setService(findServiceResult.get());

        if (!errorReportList.isEmpty()) {
            throw new ProjectException(errorReportList);
        }
    }

    @Override
    protected void beforeUpdate(BookingEntity oldEntity, BookingEntity newEntity) throws ProjectException {
        List<ErrorReport> errorReportList = new ArrayList<>();

        if (newEntity.getStartedAt() != null &&
                (newEntity.getStartedAt().isBefore(LocalDateTime.now()) ||
                        newEntity.getStartedAt().isBefore(oldEntity.getStartedAt()))) {
            errorReportList.add(new ErrorReport(
                    "BookingService_beforeUpdate",
                    ErrorEnum.ValidationError,
                    "The start time must be after the current time and after the previous start time!"));
        }

        if (!errorReportList.isEmpty()) {
            throw new ProjectException(errorReportList);
        }
    }

    @Override
    public BookingEntity delete(String id) throws ProjectException {
        return null;
    }

    @Transactional
    public BookingEntity assignVeterian(String bookingId, String veterianEmail){
        Optional<AccountEntity> findVeterian = accountsRepository.findByEmail(veterianEmail);
        if(findVeterian.isEmpty()) throw new Error("Cannot find veterian with this email " + veterianEmail);
        AccountEntity veterian = findVeterian.get();
        if (veterian.getRole() != AccountRoleEnum.VETERIAN)
            throw new Error("This email" + veterianEmail +" is not a veterian");


        Optional<BookingEntity> findBooking = bookingRepository.findById(bookingId);
        if(findBooking.isPresent()){
            BookingEntity booking = findBooking.get();
            if (booking.getStatusEnum() != StatusEnum.PENDING) throw new Error("Booking is not in PENDING state");
            booking.setVeterian(veterian);
            booking.setStatusEnum(StatusEnum.CONFIRMED);
            booking = bookingRepository.save(booking);

            // Remove all invitations
            notificationsRepository.deleteAllByBooking(booking);
            return booking;
        }
        throw new Error("Cannot find booking is not a veterian");
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

}
