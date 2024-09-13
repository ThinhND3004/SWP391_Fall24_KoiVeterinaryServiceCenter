package com.example.swp391_fall24_be.apis.accounts.dtos;

import com.example.swp391_fall24_be.apis.accounts.Account;
import com.example.swp391_fall24_be.core.IDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class UpdateAccountDto implements IDto<Account> {
    @NotBlank(message = "First name is required!")
    @Max(value = 20, message = "Length of first name must not exceed 20 letters!")
    private String firstName;
    @NotBlank(message = "First name is required!")
    @Max(value = 20, message = "Length of first name must not exceed 20 letters!")
    private String lastName;
    @NotBlank(message = "Date of birth is required!")
    private LocalDate dob;
    @NotBlank(message = "Phone is required!")
    @Pattern(regexp = "^(0[235789])(\\d{8})$|^(02[0-9]{1,2})(\\d{6,8})$\n", message = "Invalid phone number!")
    private String phone;

    @NotBlank(message = "Address is required!")
    @Max(value = 200, message = "Length of address must not exceed 200 letters!")
    private String address;

    @Override
    public Account toEntity() {
        Account account = new Account();
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setDob(dob);
        account.setPhone(phone);
        return account;
    }
}
