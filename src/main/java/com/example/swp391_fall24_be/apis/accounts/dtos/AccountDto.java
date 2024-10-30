package com.example.swp391_fall24_be.apis.accounts.dtos;

import com.example.swp391_fall24_be.apis.accounts.AccountRoleEnum;
import com.example.swp391_fall24_be.apis.images.ImageEntity;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AccountDto {
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String phone;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String address;
    private boolean isDisable;
    private AccountRoleEnum role;
    private String imageEntityId;
}
