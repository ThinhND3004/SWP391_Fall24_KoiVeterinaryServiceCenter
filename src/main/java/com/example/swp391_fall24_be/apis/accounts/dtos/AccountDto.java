package com.example.swp391_fall24_be.apis.accounts.dtos;

import com.example.swp391_fall24_be.apis.roles.Role;
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
    private Role role;

    public void setRole(Role role) {
        this.role = role;
    }
}
