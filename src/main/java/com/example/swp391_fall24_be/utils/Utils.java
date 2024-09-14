package com.example.swp391_fall24_be.utils;

import com.example.swp391_fall24_be.dto.AccountResponseDto;
import com.example.swp391_fall24_be.entities.UserEntity;

public class Utils {
    public static AccountResponseDto accountResponse(UserEntity account) {
        var dto = new AccountResponseDto();
        dto.setId(account.getId());
        dto.setEmail(account.getEmail());
        dto.setRole(account.getRole());
        return dto;
    }
}
