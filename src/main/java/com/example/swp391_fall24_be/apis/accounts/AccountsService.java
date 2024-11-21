package com.example.swp391_fall24_be.apis.accounts;

import com.example.swp391_fall24_be.apis.accounts.dtos.*;
import com.example.swp391_fall24_be.apis.bookings.BookingEntity;
import com.example.swp391_fall24_be.apis.bookings.BookingRepository;
import com.example.swp391_fall24_be.apis.bookings.MeetingMethodEnum;
import com.example.swp391_fall24_be.apis.bookings.StatusEnum;
import com.example.swp391_fall24_be.apis.services.ServiceEntity;
import com.example.swp391_fall24_be.apis.services.ServicesRepository;
import com.example.swp391_fall24_be.apis.timetables.TimetableEntity;
import com.example.swp391_fall24_be.apis.timetables.TimetableRepository;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ErrorEnum;
import com.example.swp391_fall24_be.core.ErrorReport;
import com.example.swp391_fall24_be.core.ProjectException;
import com.example.swp391_fall24_be.sub_class.TimeRange;
import com.example.swp391_fall24_be.sub_class.TimeSlot;
import com.example.swp391_fall24_be.utils.CryptoUtils;
import com.example.swp391_fall24_be.utils.TimeUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<ErrorReport> errors = new ArrayList<>();

        if (!oldEntity.getPhone().equals(newEntity.getPhone()))
        {
            Optional<AccountEntity> findPhoneResult = accountsRepository.findByPhone(newEntity.getPhone());
            if(findPhoneResult.isPresent()){
                errors.add(new ErrorReport("AccountsService_beforeCreate", ErrorEnum.FieldDuplicated,"This phone has been registered!"));
            }
        }


        if(!errors.isEmpty()){
            throw new ProjectException(errors);
        }

        oldEntity.setFirstName(newEntity.getFirstName());
        oldEntity.setLastName(newEntity.getLastName());
        oldEntity.setUpdateAt(LocalDateTime.now());
        oldEntity.setPhone(newEntity.getPhone());
        oldEntity.setAddress(newEntity.getAddress());
        oldEntity.setDob(newEntity.getDob());
        oldEntity.setIsDisable(Boolean.FALSE);
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
    @Transactional
    public boolean updateStatus(UpdateStatusAccountDto dto){
        int result = accountsRepository.updateAccountStatus(dto.getEmail(),dto.getStatus());
        return result > 0;
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
            if(veterian.getProfile() == null) continue;

            for(TimetableEntity timetable : veterian.getProfile().getTimetables()){
                    if(
                            searchTime.getDayOfWeek() == timetable.getDayOfWeek() &&
                            (searchTime.toLocalTime().isBefore(timetable.getEndTime()) &&
                            searchEndTime.toLocalTime().isAfter(timetable.getStartTime()))
                    )
                    {
                        isInTimetable = true;
                        break;
                    }
            }

            if(isInTimetable){
                // Check if searchTime is not in Booking time
                List<BookingEntity> veterianBookingList = bookingRepository.
                        findByVeterianAndStatusEnum(veterian, StatusEnum.CONFIRMED);
                for (BookingEntity booking: veterianBookingList) {
                    LocalDateTime bookingEndTime = TimeUtils.setLocalDateEndTime(booking.getStartedAt(),
                            booking.getService().getEstimatedTime());
                    // Find if search time is in Booking that has been reserved
                    //    1. Check if start time is in Booking
                    //    2. Check if end time is in Booking
                    if ((searchTime.isBefore(bookingEndTime) && searchEndTime.isAfter(booking.getStartedAt()))) {
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
                findByVeterianAndStatusEnum(account, StatusEnum.CONFIRMED);

        // Get time slot in around 7 days
        for (int i = 0; i <= 7; i++) {
            LocalDate currentDate = today.plusDays(i);
            List<TimetableEntity> timetableList = timetableRepository.
                    findByProfileAndDayOfWeekOrderByStartTimeAsc(account.getProfile(),currentDate.getDayOfWeek());

            TimeSlot timeSlot = new TimeSlot();
            timeSlot.setDate(currentDate);

            for(TimetableEntity timetable : timetableList){
                // Exclude time slot that appear in booking
                    List<TimeRange> timeSlotPerBooking = new ArrayList<>();
                    LocalTime slotStartTime = timetable.getStartTime();
                    LocalTime estimatedTime = bookedService.getEstimatedTime();
                    LocalTime slotEndTime = TimeUtils.setLocalEndTime(slotStartTime,estimatedTime);
                    while (!slotEndTime.isAfter(timetable.getEndTime())){
                            //Check if it is current day and timetable == booking day of week
                        for (BookingEntity booking: veterianBookingList){
                            if(timetable.getDayOfWeek() == booking.getStartedAt().getDayOfWeek() &&
                                currentDate.equals(booking.getStartedAt().toLocalDate())
                        ) {


                            LocalTime bookingStartTime = booking.getStartedAt().toLocalTime();
                            LocalTime bookingServiceEstimatedTime = booking.getService().getEstimatedTime();
                            LocalTime bookingEndTime = TimeUtils.setLocalEndTime(bookingStartTime, bookingServiceEstimatedTime);
                            // Check if there is a booking in the time slot
                            System.out.println("Timetable slot: "+slotStartTime + " - " + slotEndTime);
                            System.out.println("    Booking slot: "+ bookingStartTime + " - " + bookingEndTime);

                                if ((slotStartTime.isBefore(bookingEndTime) && slotEndTime.isAfter(bookingStartTime))) {
                                    System.out.println("DAY "+currentDate + ": "+slotStartTime + " - "+slotEndTime);
                                    System.out.println("Booking "+ booking.getId() + ": " + booking.getStartedAt());
                                    // Update slot start time
                                    slotStartTime = bookingEndTime;
                                    slotEndTime = TimeUtils.setLocalEndTime(slotStartTime, estimatedTime);
                                }
                            }
                        }
                    }
                    timeSlotPerBooking.add(new TimeRange(slotStartTime,slotEndTime));

                    slotStartTime = TimeUtils.setLocalEndTime(slotStartTime, estimatedTime);
                    slotEndTime = TimeUtils.setLocalEndTime(slotStartTime,estimatedTime);

                    if(timeSlot.getSlots().isEmpty() ||
                            timeSlot.getSlots().size() > timeSlotPerBooking.size()
                    ){
                        timeSlot.setSlots(timeSlotPerBooking);
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
            if(veterian.getProfile() == null) continue;
            veterianRespDtos.add(
                    veterian.toVeterianResponseDto(getValidTimeSlot(veterian,bookedService))
            );
        }
        return veterianRespDtos;
    }

    public List<TimeSlot> getTimeSlotByDate(AccountEntity account, ServiceEntity service, LocalDate date) {
        List<TimeSlot> timeSlotList = new ArrayList<>();

        // Lấy danh sách timetable của bác sĩ theo ngày
        List<TimetableEntity> timetableList = timetableRepository
                .findByProfileAndDayOfWeekOrderByStartTimeAsc(account.getProfile(), date.getDayOfWeek());

        for (TimetableEntity timetable : timetableList) {
            TimeSlot timeSlot = new TimeSlot();
            timeSlot.setDate(date);

            List<TimeRange> timeRanges = new ArrayList<>();
            LocalTime slotStartTime = timetable.getStartTime();

            // Tính estimatedMinutes dựa trên dịch vụ
            int estimatedMinutes = service.getEstimatedTime().getHour() * 60 + service.getEstimatedTime().getMinute();

            // Thời gian trống tùy thuộc vào loại dịch vụ
            int bufferTimeMinutes = 0;
            switch (service.getMeetingMethod()) {
                case ONLINE:
                case OFFLINE_CENTER:
                    bufferTimeMinutes = 15; // Thêm 15 phút nghỉ
                    break;
                case OFFLINE_HOME:
                    bufferTimeMinutes = 60; // Thêm 1 tiếng di chuyển
                    break;
                default:
                    break;
            }

            LocalTime slotEndTime = slotStartTime.plusMinutes(estimatedMinutes);

            // Tạo các slots dựa trên timetable của bác sĩ
            while (!slotEndTime.isAfter(timetable.getEndTime())) {
                LocalTime currentSlotStartTime = slotStartTime;
                LocalTime currentSlotEndTime = slotEndTime;

                // Kiểm tra nếu slot đã được đặt
                boolean isBooked = timetable.getBookedSlots().stream()
                        .anyMatch(range ->
                                currentSlotStartTime.isBefore(range.getEndTime()) && currentSlotEndTime.isAfter(range.getStartTime())
                        );

                // Nếu slot chưa được đặt, thêm vào danh sách
                if (!isBooked) {
                    timeRanges.add(new TimeRange(currentSlotStartTime, currentSlotEndTime));
                }

                // Cập nhật thời gian slot với buffer time
                slotStartTime = slotEndTime.plusMinutes(bufferTimeMinutes);
                slotEndTime = slotStartTime.plusMinutes(estimatedMinutes);
            }

            // Nếu có thời gian trống, thêm vào danh sách
            if (!timeRanges.isEmpty()) {
                timeSlot.setSlots(timeRanges);
                timeSlotList.add(timeSlot);
            }
        }

        return timeSlotList;
    }

    public AccountEntity getAccountByEmail(String email) {
        Optional<AccountEntity> optionalAccount = accountsRepository.findByEmail(email);
        return optionalAccount.get();
    }

    public AccountEntity updateAccountByEmail(String email, UpdateAccountDto updateAccountDto) throws ProjectException {
        AccountEntity oldAccountEntity = getAccountByEmail(email);
        AccountEntity updateAccountEntity = updateAccountDto.toEntity();
        //set new fields
        oldAccountEntity.setFirstName(updateAccountEntity.getFirstName());
        oldAccountEntity.setLastName(updateAccountEntity.getLastName());
        oldAccountEntity.setDob(updateAccountEntity.getDob());
        oldAccountEntity.setPhone(updateAccountEntity.getPhone());
        oldAccountEntity.setAddress(updateAccountEntity.getAddress());
        oldAccountEntity.setUpdateAt(LocalDateTime.now());

        oldAccountEntity = accountsRepository.save(oldAccountEntity);
        return oldAccountEntity;
    }

    public int changePassword(ChangePasswordRequest request) {
//        Optional<AccountEntity> accountOptional = accountsRepository.findByEmail(request.getEmail());
        AccountEntity foundAccount = getAccountByEmail(request.getEmail());
        // Khởi tạo response mặc định
        int response = -1;

        // Kiểm tra tài khoản có tồn tại
        if (foundAccount != null) {
//            AccountEntity account = accountOptional.get();

            // Xác minh mật khẩu cũ
            if (cryptoUtils.verify(request.getOldPassword(), foundAccount.getPassword())) {
                // Mã hóa mật khẩu mới
                String newHashedPassword = cryptoUtils.crypto(request.getNewPassword());
                foundAccount.setPassword(newHashedPassword);

                // Lưu thay đổi vào cơ sở dữ liệu
                accountsRepository.save(foundAccount);
                response = 1;
//                System.out.println(response.getMessage());
            } else {
                response = 0;
//                System.out.println(response.getMessage());
            }
        } else {
            response = -1;
//            System.out.println(response.getMessage());
        }
//        System.out.println(response.getMessage());
        return response;
    }

    public int verifyPassword(PasswordVerificationRequest request) {
        int response = -1;
        AccountEntity foundAccount = getAccountByEmail(request.getEmail());
        if (foundAccount != null) {
            // Xác minh mật khẩu cũ
            if (cryptoUtils.verify(request.getPassword(), foundAccount.getPassword())) {
                response = 1;
            } else {
                response = 0;
            }
        } else {
            response = -1;
        }
        return response;
    }
}
