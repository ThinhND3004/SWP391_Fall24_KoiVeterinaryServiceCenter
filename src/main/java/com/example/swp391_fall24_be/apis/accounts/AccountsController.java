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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/idle-veterian-by-time/{startDate}")
    public ResponseDto<List<AccountDto>> getIdleVeterianByTime(
            @Param("startDate") LocalDateTime startTime
            ){
        return new ResponseDto<>(
                HttpStatus.OK.value(),
                "Get current account from token success!",
                accountsService.findIdleAccountByTime(startTime),
                null
        );
    }

    @GetMapping("/veterian-with-time-slot/{service-id}")
    public ResponseDto<List<VeterianRespDto>> getVeterianWithTimeSlot(
            @Param("service-id") String serviceId
    ){
        return new ResponseDto<>(
                HttpStatus.OK.value(),
                "Get current account from token success!",
                accountsService.findVeterianWithTimeSlot(serviceId),
                null
        );
    }


}