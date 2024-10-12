package com.example.swp391_fall24_be.apis.accounts;

import com.example.swp391_fall24_be.apis.accounts.dtos.AccountDto;
import com.example.swp391_fall24_be.apis.accounts.dtos.CreateAccountDto;
import com.example.swp391_fall24_be.apis.accounts.dtos.PaginateAccountDto;
import com.example.swp391_fall24_be.apis.accounts.dtos.UpdateAccountDto;
import com.example.swp391_fall24_be.core.AbstractController;
import com.example.swp391_fall24_be.core.ResponseDto;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@Tag(name = "Accounts", description = "Account APIs")
@CrossOrigin
public class AccountsController extends AbstractController<AccountEntity, String, CreateAccountDto, UpdateAccountDto, PaginateAccountDto, AccountDto> {

    @Autowired
    private AccountsService accountsService;

    @GetMapping("/getInfo")
    public ResponseDto<AccountDto> getAccInfo(@Parameter String token) {
        try {
            return new ResponseDto<>(
                    HttpStatus.OK.value(),
                    "Get account's info successfully!",
                    accountsService.getAccountByVerifyToken(token).toResponseDto(),
                    null
            );
        }
        catch (Exception e){
            return new ResponseDto<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "Cannot get account's info!",
                    null,
                    e.getMessage()
            );
        }
    }
}