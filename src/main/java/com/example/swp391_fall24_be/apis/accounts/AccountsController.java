package com.example.swp391_fall24_be.apis.accounts;

import com.example.swp391_fall24_be.annotations.CurrentAccount;
import com.example.swp391_fall24_be.apis.accounts.dtos.AccountDto;
import com.example.swp391_fall24_be.apis.accounts.dtos.CreateAccountDto;
import com.example.swp391_fall24_be.apis.accounts.dtos.PaginateAccountDto;
import com.example.swp391_fall24_be.apis.accounts.dtos.UpdateAccountDto;
import com.example.swp391_fall24_be.core.AbstractController;
import com.example.swp391_fall24_be.core.ResponseDto;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@Tag(name = "Accounts", description = "Account APIs")
public class AccountsController extends AbstractController<AccountEntity, String, CreateAccountDto, UpdateAccountDto, PaginateAccountDto, AccountDto> {

    @GetMapping("/current")
    public ResponseDto<AccountDto> getAccountDetails(@Parameter(hidden = true) @CurrentAccount AccountEntity account) {
        try {
            return new ResponseDto<>(
                    HttpStatus.OK.value(),
                    "Get current account from token success!",
                    account.toResponseDto(),
                    null
            );
        }
        catch (Exception e){
            List<String> errorList = new ArrayList<>();
            errorList.add(e.getMessage());
            return new ResponseDto<>(
                    HttpStatus.OK.value(),
                    "Get current account from token fail!",
                    null,
                    errorList
            );
        }
    }
}