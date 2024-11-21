package com.example.swp391_fall24_be.apis.accounts;

import com.example.swp391_fall24_be.apis.accounts.dtos.*;
import com.example.swp391_fall24_be.apis.bookings.MeetingMethodEnum;
import com.example.swp391_fall24_be.apis.services.ServiceEntity;
import com.example.swp391_fall24_be.apis.services.ServicesRepository;
import com.example.swp391_fall24_be.apis.services.ServicesService;
import com.example.swp391_fall24_be.core.AbstractController;
import com.example.swp391_fall24_be.core.ProjectException;
import com.example.swp391_fall24_be.core.ResponseDto;
import com.example.swp391_fall24_be.sub_class.TimeRange;
import com.example.swp391_fall24_be.sub_class.TimeSlot;
import com.example.swp391_fall24_be.utils.AuthUtils;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accounts")
@Tag(name = "Accounts", description = "Account APIs")
public class AccountsController extends AbstractController<AccountEntity, String, CreateAccountDto, UpdateAccountDto, PaginateAccountDto, AccountDto> {

    @Autowired
    private AccountsService accountsService;

    @GetMapping("/current")
    public ResponseDto<AccountDto> getAccountDetails() {
        try {
            AccountDto dto = AuthUtils.getCurrentAccount().toResponseDto();
            return new ResponseDto<>(
                    HttpStatus.OK.value(),
                    "Get current account from token success!",
                    dto,
                    null
            );
        }
        catch (Exception e){
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseDto<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "Cannot get current account details!",
                    null,
                    errors
            );
        }
    }

    @GetMapping("/search-by-name/{searchName}")
    public ResponseDto<List<AccountDto>> getSearchAccounts(
            PaginateAccountDto paginateAccountDto,
            @PathVariable("searchName") String searchName
    ) {
        return new ResponseDto<>(
                HttpStatus.OK.value(),
                "Get current account from token success!",
                accountsService.getAccountsBySearchFullName(paginateAccountDto, searchName),
                null
        );
    }

    @PostMapping("/update-status")
    public ResponseDto<Boolean> updateStatus(
            @RequestBody UpdateStatusAccountDto dto
    ){
        return new ResponseDto<>(
                HttpStatus.OK.value(),
                "Update status for account successful",
                accountsService.updateStatus(dto),
                null
        );
    }

    @GetMapping("/idle-veterian-by-time/{serviceId}/{startDateTime}")
    public ResponseDto<List<VeterianRespDto>> getIdleVeterianByTime(
            @PathVariable String serviceId,
            @PathVariable LocalDateTime startDateTime
    ){
        return new ResponseDto<>(
                HttpStatus.OK.value(),
                "Get idle veterian by time successful!",
                accountsService.findIdleAccountByTime(serviceId,startDateTime),
                null
        );
    }

    @GetMapping("/veterian-with-time-slot/{serviceId}")
    public ResponseDto<List<VeterianRespDto>> getVeterianWithTimeSlot(
            @PathVariable String serviceId
    ){
        return new ResponseDto<>(
                HttpStatus.OK.value(),
                "Get veterian with time slot successful!",
                accountsService.findVeterianWithTimeSlot(serviceId),
                null
        );
    }

//    @Autowired
//    ServicesService servicesService;
//    @GetMapping("/by-date")
//    public ResponseEntity<?> getTimeSlotsByDate(
//            @RequestParam("veterianEmail") String veterianEmail,
//            @RequestParam("serviceId") String bookedService,
//            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
//        try {
//            // Lấy thông tin bác sĩ từ ID
//            AccountEntity account = accountsService.getAccountByEmail(veterianEmail);
//
//            ServiceEntity serviceEntity = servicesService.findById(bookedService);
//
//            // Gọi hàm để lấy TimeSlot
//            List<TimeSlot> timeSlots = accountsService.getTimeSlotByDate(account, serviceEntity, date);
//
//            // Trả về danh sách TimeSlot
//            return ResponseEntity.ok(timeSlots);
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veterian not found!");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
//        }
//    }

    @Autowired
    AccountsRepository accountsRepository;
//    @PostMapping("/veterians/book-slot")
//    public ResponseEntity<String> bookSlot(
//            @RequestParam String veterianEmail,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
//            @RequestParam MeetingMethodEnum meetingMethod,
//            @RequestBody TimeRange bookedSlot) {
//
//        // Tìm bác sĩ
//        AccountEntity veterian = accountsService.getAccountByEmail(veterianEmail);
//
//        // Xử lý cập nhật giờ làm việc
//        accountsService.updateVeterianWorkingSlots(veterian, date, bookedSlot, meetingMethod);
//
//        return ResponseEntity.ok("Slot booked and working schedule updated");
//    }


    @PutMapping("/update-by-email/{email}")
    public ResponseDto<AccountDto> updateByEmail(@PathVariable String email,
                                                 @RequestBody UpdateAccountDto updateAccountDto)
            throws ProjectException {
        return new ResponseDto<>(
                HttpStatus.OK.value(),
                "Search current account success!",
                accountsService.updateAccountByEmail(email, updateAccountDto).toResponseDto(),
                null
        );
    }

    @PostMapping("/verify-password")
    public ResponseDto<Void> verifyPassword(@RequestBody PasswordVerificationRequest request){
        int response = accountsService.verifyPassword(request);
        int status = 500;
        String message = "Internal Server Error";
        if (response == 1){
            status = HttpStatus.OK.value();
            message = "Password matched!";
        } else if (response == 0){
            status = HttpStatus.NOT_FOUND.value();
            message = "Password not matched!";
        } else {
            status = HttpStatus.NOT_FOUND.value();
            message = "Account not found!";
        }

        return new ResponseDto<>(status, message, null, null);
    }

    @PostMapping("/change-password")
    public ResponseDto<Void> changePassword(@RequestBody ChangePasswordRequest request){
        int response = accountsService.changePassword(request);
        int status = 500;
        String message = "Internal Server Error";
        if (response == 1){
            status = HttpStatus.OK.value();
            message = "Change password successfully!";
        } else if (response == 0){
            status = HttpStatus.NOT_FOUND.value();
            message = "Old password is not matched!";
        } else {
            status = HttpStatus.NOT_FOUND.value();
            message = "Account not found!";
        }

        return new ResponseDto<>(status, message, null, null);
    }
}