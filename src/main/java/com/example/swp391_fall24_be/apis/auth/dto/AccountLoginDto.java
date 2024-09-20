package com.example.swp391_fall24_be.apis.auth.dto;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.core.IDto;
import lombok.Data;

@Data
public class AccountLoginDto implements IDto<AccountEntity> {
    private String email;
    private String password;

    @Override
    public AccountEntity toEntity() {
        var account = new AccountEntity();
        account.setEmail(email);
        account.setPassword(password);
        return account;
    }
}
