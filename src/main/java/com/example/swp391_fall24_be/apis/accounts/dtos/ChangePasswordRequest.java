package com.example.swp391_fall24_be.apis.accounts.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChangePasswordRequest {
    private String email;
    private String oldPassword;
    private String newPassword;
}
