package com.example.swp391_fall24_be.apis.accounts;

import com.example.swp391_fall24_be.annotations.CurrentAccount;
import com.example.swp391_fall24_be.apis.accounts.dtos.*;
import com.example.swp391_fall24_be.core.AbstractController;
import com.example.swp391_fall24_be.core.ResponseDto;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@Tag(name = "Accounts", description = "Account APIs")
public class AccountsController extends AbstractController<AccountEntity, String, CreateAccountDto, UpdateAccountDto, PaginateAccountDto, AccountDto> {

    @Autowired
    private AccountsService accountsService;
    @GetMapping("/current")
    public ResponseDto<AccountDto> getAccountDetails(@Parameter(hidden = true) @CurrentAccount AccountEntity account) {
        return new ResponseDto<>(
            HttpStatus.OK.value(),
            "Get current account from token success!",
            account.toResponseDto(),
            null
        );
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


}