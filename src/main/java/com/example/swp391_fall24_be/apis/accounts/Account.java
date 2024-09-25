package com.example.swp391_fall24_be.apis.accounts;

import com.example.swp391_fall24_be.apis.accounts.dtos.AccountDto;
import com.example.swp391_fall24_be.core.IObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "accounts")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account implements IObject<AccountDto>{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true, columnDefinition = "NVARCHAR(100)")
    private String email;

    @Column (columnDefinition = "NVARCHAR(128)")
    private String password;

    @Column (nullable = false, name = "first_name", columnDefinition = "NVARCHAR(20)")
    private String firstName;

    @Column (nullable = false, name = "last_name", columnDefinition = "NVARCHAR(20)")
    private String lastName;

    @Column (nullable = false)
    @CreatedDate
    private LocalDate dob;

    @Column (nullable = false, unique = true, columnDefinition = "CHAR(10)")
    private String phone;

    @Column (nullable = false, columnDefinition = "NVARCHAR(200)")
    private String address;

    @Column (nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createAt;

    @Column (nullable = false)
    @LastModifiedDate
    private LocalDateTime updateAt;

    @Column (nullable = false, name = "is_disable")
    private boolean isDisable;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private AccountRoleEnum role;

    public Account(String id) {
        this.id = id;
    }

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
