package com.example.swp391_fall24_be.dto;

import com.example.swp391_fall24_be.core.IDto;
import com.example.swp391_fall24_be.entities.UserEntity;
import lombok.Data;

@Data
public class AccountUpdateDto implements IDto<UserEntity> {
    private String email;

    @Override
    public UserEntity toEntity() {
        var account = new UserEntity();
        account.setEmail(email);
        return account;
    }
}
