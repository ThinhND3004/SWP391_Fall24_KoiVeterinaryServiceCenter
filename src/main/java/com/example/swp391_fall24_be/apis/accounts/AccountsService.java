package com.example.swp391_fall24_be.apis.accounts;

import com.example.swp391_fall24_be.apis.accounts.dtos.*;
import com.example.swp391_fall24_be.apis.bookings.BookingEntity;
import com.example.swp391_fall24_be.apis.bookings.BookingRepository;
import com.example.swp391_fall24_be.apis.bookings.StatusEnum;
import com.example.swp391_fall24_be.apis.profiles.ProfileEntity;
import com.example.swp391_fall24_be.apis.profiles.ProfileRepository;
import com.example.swp391_fall24_be.apis.services.ServiceEntity;
import com.example.swp391_fall24_be.apis.services.ServicesRepository;
import com.example.swp391_fall24_be.apis.timetables.DTOs.TimetableTime;
import com.example.swp391_fall24_be.apis.timetables.TimetableEntity;
import com.example.swp391_fall24_be.apis.timetables.TimetableRepository;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ErrorReport;
import com.example.swp391_fall24_be.core.ErrorEnum;
import com.example.swp391_fall24_be.core.ProjectException;
import com.example.swp391_fall24_be.sub_class.TimeRange;
import com.example.swp391_fall24_be.sub_class.TimeSlot;
import com.example.swp391_fall24_be.utils.CryptoUtils;
import com.example.swp391_fall24_be.utils.TimeUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountsService extends AbstractService<AccountEntity, String, CreateAccountDto, UpdateAccountDto, PaginateAccountDto> {
    @Autowired
    private CryptoUtils cryptoUtils;

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TimetableRepository timetableRepository;

    @Override
    protected void beforeCreate(AccountEntity account) throws ProjectException {
        List<ErrorReport> errorList = new ArrayList<>();

        Optional<AccountEntity> findEmailResult = accountsRepository.findByEmail(account.getEmail());
        if(findEmailResult.isPresent()){
            errorList.add(new ErrorReport("AccountsService_beforeCreate", ErrorEnum.FieldDuplicated,"This email has been registered!"));
        }

        Optional<AccountEntity> findPhoneResult = accountsRepository.findByPhone(account.getPhone());
        if(findPhoneResult.isPresent()){
            errorList.add(new ErrorReport("AccountsService_beforeCreate", ErrorEnum.FieldDuplicated,"This phone has been registered!"));
        }

        if(account.getPassword() != null && errorList.isEmpty()) {
            account.setPassword(cryptoUtils.crypto(account.getPassword()));
        }

        if(!errorList.isEmpty()){
            throw new ProjectException(errorList);
        }
    }

    @Override
    protected void beforeUpdate(AccountEntity oldEntity, AccountEntity newEntity) throws ProjectException {

    }

    @Override
    public AccountEntity delete(String id) throws ProjectException {
        return null;
    }

    public List<AccountDto> getAccountsBySearchFullName(PaginateAccountDto paginateAccountDto, String searchValue){
        Pageable pageable = paginateAccountDto.getPageRequest();
        List<AccountEntity> accounts = accountsRepository.findBySearchFullName(searchValue,paginateAccountDto.role,pageable);
        List<AccountDto> accountDtos = new ArrayList<>();
        for (AccountEntity account : accounts){
            accountDtos.add(account.toResponseDto());
        }
        return accountDtos;
    }

    private ServiceEntity getServiceById(String id){
        Optional<ServiceEntity> findServiceResult = servicesRepository.findById(id);
        if(findServiceResult.isEmpty()){
            throw new EntityNotFoundException("Service not found!");
        }
        return findServiceResult.get();
    }

    public List<VeterianRespDto> findIdleAccountByTime(String serviceId, LocalDateTime searchTime){
        // Check if veterian work on that time
        List<AccountEntity> idleVeterians = accountsRepository.findAllByRoleAndIsDisable(AccountRoleEnum.VETERIAN, false);
        List<VeterianRespDto> veterianDtos = new ArrayList<>();
        ServiceEntity bookedService = getServiceById(serviceId);
        LocalTime estimatedTime = bookedService.getEstimatedTime();

        LocalDateTime searchEndTime = searchTime.plusHours(estimatedTime.getHour())
                .plusMinutes(estimatedTime.getMinute());

        for(AccountEntity veterian : idleVeterians){
            // Check if the searchTime is in timetable
            boolean isInTimetable = false;
            boolean isInBooking = false;
            System.out.println(veterian.getProfile().getId());
            System.out.println(veterian.getProfile().getTimetables().toString());

            for(TimetableEntity timetable : veterian.getProfile().getTimetables()){

                    if(
                            searchTime.getDayOfWeek() == timetable.getDayOfWeek() &&
                            (!searchTime.toLocalTime().isBefore(timetable.getStartTime()) &&
                            !searchEndTime.toLocalTime().isAfter(timetable.getEndTime()))
                    )
                    {
                        isInTimetable = true;
                        break;
                    }
            }

            if(isInTimetable){
                // Check if searchTime is not in Booking time
                List<BookingEntity> veterianBookingList = bookingRepository.
                        findByVeterianAndStatusEnumOrStatusEnumOrderByStartedAtAsc(veterian, StatusEnum.CONFIRMED, StatusEnum.PENDING);
                for (BookingEntity booking: veterianBookingList) {
                    LocalDateTime bookingEndTime = TimeUtils.setLocalDateEndTime(booking.getStartedAt(),
                            booking.getService().getEstimatedTime());

                    // Find if search time is in Booking that has been reserved
                    //    1. Check if start time is in Booking
                    //    2. Check if end time is in Booking
                    if (
                            (searchTime.isAfter(booking.getStartedAt()) && searchTime.isBefore(bookingEndTime)
                            || searchEndTime.isAfter(booking.getStartedAt()) && searchEndTime.isBefore(bookingEndTime))
                            || (searchTime.equals(booking.getStartedAt()) && searchEndTime.equals(bookingEndTime))
                    ) {
                        isInBooking = true;
                        break;
                    }
                }
            }

            if(isInTimetable && !isInBooking){
                veterianDtos.add(veterian.toVeterianResponseDto(null));
            }
        }
        return veterianDtos;
    }

    private List<TimeSlot>  getValidTimeSlot(AccountEntity account, ServiceEntity bookedService){
        List<TimeSlot> timeSlotList = new ArrayList<>();
        LocalDate today = LocalDate.now();

        List<BookingEntity> veterianBookingList = bookingRepository.
                findByVeterianAndStatusEnumOrStatusEnumOrderByStartedAtAsc(account, StatusEnum.CONFIRMED, StatusEnum.PENDING);

        // Get time slot in around 7 days
        for (int i = 0; i <= 7; i++) {
            LocalDate currentDate = today.plusDays(i);
            List<TimetableEntity> timetableList = timetableRepository.
                    findByProfileAndDayOfWeekOrderByStartTimeAsc(account.getProfile(),currentDate.getDayOfWeek());

            TimeSlot timeSlot = new TimeSlot();
            timeSlot.setDate(currentDate);

            for(TimetableEntity timetable : timetableList){
                // Exclude time slot that appear in booking
                for (BookingEntity booking: veterianBookingList){
                    List<TimeRange> timeSlotPerBooking = new ArrayList<>();
                    LocalTime slotStartTime = timetable.getStartTime();
                    LocalTime estimatedTime = bookedService.getEstimatedTime();
                    LocalTime slotEndTime = TimeUtils.setLocalEndTime(slotStartTime,estimatedTime);
                    while (!slotEndTime.isAfter(timetable.getEndTime())){
                            //Check if it is current day and timetable == booking day of week
                        System.out.println("DATE COMPARE: " + currentDate + " - " +booking.getStartedAt().toLocalDate() + " = "+
                                currentDate.equals(booking.getStartedAt().toLocalDate()));
                        System.out.println("DAY OF WEEK: " + timetable.getDayOfWeek() + " - " + booking.getStartedAt().getDayOfWeek() + " = " +
                                (timetable.getDayOfWeek() == booking.getStartedAt().getDayOfWeek()));
                        if(timetable.getDayOfWeek() == booking.getStartedAt().getDayOfWeek() &&
                                currentDate.equals(booking.getStartedAt().toLocalDate())
                        ) {

                            LocalTime bookingStartTime = booking.getStartedAt().toLocalTime();
                            LocalTime bookingServiceEstimatedTime = booking.getService().getEstimatedTime();
                            LocalTime bookingEndTime = TimeUtils.setLocalEndTime(bookingStartTime, bookingServiceEstimatedTime);
                                // Check if there is a booking in the time slot

                                if (
                                        (slotStartTime.isAfter(bookingStartTime) && slotStartTime.isBefore(bookingEndTime)
                                        || slotEndTime.isAfter(bookingStartTime) && slotEndTime.isBefore(bookingEndTime))
                                        || (slotStartTime.equals(bookingStartTime) && slotEndTime.equals(bookingEndTime))
                                ) {
                                    // Update status
                                    slotStartTime = bookingEndTime;
                                    slotEndTime = TimeUtils.setLocalEndTime(slotStartTime, estimatedTime);
                                    continue;
                                }
                            }
                            timeSlotPerBooking.add(new TimeRange(slotStartTime,slotEndTime));
                            slotStartTime = TimeUtils.setLocalEndTime(slotStartTime, estimatedTime);
                            slotEndTime = TimeUtils.setLocalEndTime(slotStartTime,estimatedTime);
                        }
                        if(timeSlot.getSlots().isEmpty() ||
                            timeSlot.getSlots().size() > timeSlotPerBooking.size()
                        ){
                            timeSlot.setSlots(timeSlotPerBooking);
                        }
                    }
                }
                if(!timeSlot.getSlots().isEmpty()){
                    timeSlotList.add(timeSlot);
                }

            }
        return timeSlotList;
    }

    public List<VeterianRespDto> findVeterianWithTimeSlot(String serviceId){
        List<AccountEntity> veterianList = accountsRepository.findAllByRoleAndIsDisable(AccountRoleEnum.VETERIAN, false);
        List<VeterianRespDto> veterianRespDtos = new ArrayList<>();

        ServiceEntity bookedService = getServiceById(serviceId);

        for (AccountEntity veterian : veterianList){
            veterianRespDtos.add(
                    veterian.toVeterianResponseDto(getValidTimeSlot(veterian,bookedService))
            );
        }
        return veterianRespDtos;
    }
}
