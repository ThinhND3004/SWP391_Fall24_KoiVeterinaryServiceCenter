package com.example.swp391_fall24_be.apis.accounts.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateStatusAccountDto {
    @NotBlank(message = "Email is required!")
    @Email(message = "Wrong email format!")
    @Size(max = 100, message = "Length of email must not exceed 100 letters!")
    private String email;

    @NotNull(message = "Password is required!")
    private Boolean status;

}
