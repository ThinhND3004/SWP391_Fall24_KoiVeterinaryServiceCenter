package com.example.swp391_fall24_be.apis.auth.dto;

import com.example.swp391_fall24_be.apis.accounts.Account;
import com.example.swp391_fall24_be.core.IDto;
import lombok.Data;

@Data
public class AccountLoginDto implements IDto<Account> {
    private String email;
    private String password;

    @Override
    public Account toEntity() {
        var account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        return account;
    }
}
