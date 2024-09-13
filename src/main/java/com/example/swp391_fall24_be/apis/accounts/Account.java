package com.example.swp391_fall24_be.apis.accounts;

import com.example.swp391_fall24_be.apis.accounts.dtos.AccountDto;
import com.example.swp391_fall24_be.apis.roles.Role;
import com.example.swp391_fall24_be.core.IObject;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "accounts")
@Data
public class Account implements IObject<AccountDto>{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column()
    private String email;

    @Column ()
    private String password;

    @Column ()
    private String firstName;

    @Column ()
    private String lastName;

    @Column ()
    private LocalDate dob;

    @Column ()
    private String phone;

    @Column ()
    private LocalDateTime createAt;

    @Column ()
    private LocalDateTime updateAt;

    @Column ()
    private String address;
    @Column ()
    private boolean isDisable;

    @JoinColumn()
    @ManyToOne
    private Role role;

    @Override
    public AccountDto toResponseDto() {
        AccountDto dto = new AccountDto();
        dto.setEmail(email);
        dto.setDob(dob);
        dto.setAddress(address);
        dto.setPhone(phone);
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setRole(role);
        dto.setCreateAt(createAt);
        dto.setUpdateAt(updateAt);
        dto.setDisable(isDisable);
        return dto;
    }

}
