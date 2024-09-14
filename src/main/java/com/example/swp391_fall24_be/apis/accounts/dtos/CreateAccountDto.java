package com.example.swp391_fall24_be.apis.accounts.dtos;

import com.example.swp391_fall24_be.apis.accounts.Account;
import com.example.swp391_fall24_be.apis.roles.Role;
import com.example.swp391_fall24_be.core.IDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class CreateAccountDto implements IDto<Account> {
    @NotBlank(message = "Email is required!")
    @Email(message = "Wrong email format!")
    @Size(max = 100, message = "Length of email must not exceed 100 letters!")
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "Password is required!")
    @Size(max = 128, message = "Length of password must not exceed 128 letters!")
    @JsonProperty("password")
    private String password;

    @NotBlank(message = "First name is required!")
    @Size(max = 20, message = "Length of first name must not exceed 20 letters!")
    @JsonProperty("firstName")
    private String firstName;

    @NotBlank(message = "Last name is required!")
    @Size(max = 20, message = "Length of last name must not exceed 20 letters!")
    @JsonProperty("lastName")
    private String lastName;

    @NotNull(message = "Date of birth is required!")
    @JsonProperty("dob")
    private LocalDate dob;

    @NotBlank(message = "Phone is required!")
    @Pattern(regexp = "^(0[235789])(\\d{8})$|^(02[0-9]{1,2})(\\d{6,8})$", message = "Invalid phone number!")
    @JsonProperty("phone")
    private String phone;

    @NotBlank(message = "Address is required!")
    @Size(max = 200, message = "Length of address must not exceed 200 letters!")
    @JsonProperty("address")
    private String address;

    @NotBlank(message = "Role is required!")
    @JsonProperty("roleName")
    private String roleName;

    @Override
    public Account toEntity() {
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setDob(dob);
        account.setPhone(phone);
        account.setAddress(address);
        Role role = new Role();
        role.setName(roleName);
        account.setRole(role);
        return account;
    }
}
