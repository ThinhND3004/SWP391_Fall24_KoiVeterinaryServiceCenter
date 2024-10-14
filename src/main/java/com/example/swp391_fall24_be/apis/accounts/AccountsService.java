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
import com.example.swp391_fall24_be.sub_class.TimeSlot;
import com.example.swp391_fall24_be.utils.CryptoUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<AccountDto> findIdleAccountByTime(LocalDateTime searchTime){
        // Check if veterian work on that time
        List<AccountEntity> idleVeterian = accountsRepository.findIdleVeterianBySearchTime(searchTime);
        List<AccountDto> accountDtos = new ArrayList<>();
        for (AccountEntity veterian : idleVeterian){
            accountDtos.add(veterian.toResponseDto());
        }

        return accountDtos;
    }

    private LocalTime setSlotEndTime(LocalTime startTime, LocalTime estimatedTime){
        return startTime.plusHours(estimatedTime.getHour())
                .plusMinutes(estimatedTime.getMinute());
    }

    private List<TimeSlot>  getValidTimeSlot(AccountEntity account, ServiceEntity bookedService){
        List<TimeSlot> timeSlotList = new ArrayList<>();
        LocalDate today = LocalDate.now();

        // Get time slot in around 7 days
        for (int i = 0; i <= 7; i++) {
            LocalDate currentDate = today.plusDays(i);
            List<TimetableEntity> timetableList = timetableRepository.
                    findByProfileAndDayOfWeekOrderByStartTimeAsc(account.getProfile(),currentDate.getDayOfWeek());

            TimeSlot timeSlot = new TimeSlot();
            timeSlot.setDate(currentDate);

            for(TimetableEntity timetable : timetableList){
                LocalTime slotStartTime = timetable.getStartTime();
                LocalTime estimatedTime = bookedService.getEstimatedTime();
                LocalTime slotEndTime = setSlotEndTime(slotStartTime,estimatedTime);

                List<BookingEntity> veterianBookingList = bookingRepository.findByVeterianOrderByStartedAtAsc(account);
                // Exclude time slot that appear in booking
                for (BookingEntity booking: veterianBookingList){
                    // Out of range timetable
                    if(slotEndTime.isAfter(timetable.getEndTime())) break;

                    // Check if booking is finished
                    if (booking.getStatusEnum() != StatusEnum.CONFIRMED && booking.getStatusEnum() != StatusEnum.PENDING)
                        continue;

                    // Check day of week
                    if(timetable.getDayOfWeek() == booking.getStartedAt().getDayOfWeek()){
                        LocalTime bookingStartTime = booking.getStartedAt().toLocalTime();
                        LocalTime bookingServiceEstimatedTime =  booking.getService().getEstimatedTime();
                        LocalTime bookingEndTime = bookingStartTime.plusHours(bookingServiceEstimatedTime.getHour())
                                .plusMinutes(bookingServiceEstimatedTime.getMinute());
                        // Check if there is a booking in the time slot
                        if(slotStartTime.isBefore(bookingStartTime) && slotEndTime.isAfter(bookingStartTime)){
                            // Update start time
                            slotStartTime = bookingEndTime.plusHours(1);
                            slotEndTime = setSlotEndTime(slotStartTime,estimatedTime);
                            continue;
                        }

                    }

                    // Save time slots
                    if(slotEndTime.isAfter(timetable.getEndTime())) break;
                    timeSlot.getSlots().put(slotStartTime,slotEndTime);

                }
                if (!timeSlot.getSlots().isEmpty())
                    timeSlotList.add(timeSlot);
            }
        }

        return timeSlotList;
    }

    public List<VeterianRespDto> findVeterianWithTimeSlot(String serviceId){
        List<AccountEntity> veterianList = accountsRepository.findAllByRoleAndIsDisable(AccountRoleEnum.VETERIAN, false);
        List<VeterianRespDto> veterianRespDtos = new ArrayList<>();

        Optional<ServiceEntity> findServiceResult = servicesRepository.findById(serviceId);
        if(findServiceResult.isEmpty()){
            throw new EntityNotFoundException("Service not found!");
        }
        ServiceEntity bookedService = findServiceResult.get();

        for (AccountEntity veterian : veterianList){
            VeterianRespDto dto = new VeterianRespDto();
            dto.setEmail(veterian.getEmail());
            dto.setDob(veterian.getDob());
            dto.setAddress(veterian.getAddress());
            dto.setPhone(veterian.getPhone());
            dto.setRole(veterian.getRole());

            dto.setTimeSlot(getValidTimeSlot(veterian,bookedService));

            ProfileEntity profile = veterian.getProfile();
            profile.setTimetables(null);
            dto.setProfile(profile);
        }
        return veterianRespDtos;
    }
}
