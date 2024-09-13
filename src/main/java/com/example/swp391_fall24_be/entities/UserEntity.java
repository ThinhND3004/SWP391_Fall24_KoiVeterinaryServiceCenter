package com.example.swp391_fall24_be.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NotBlank
public class UserEntity {

    @Id
    private UUID id;

    @Column (name = "email")
    private String email;
    @Column (name = "password")
    private String password;

    @Column (name = "first_name")
    private String firstName;
    @Column (name = "last_name")
    private String lastName;
    @Column (name = "dob")
    private LocalDate dob;
    @Column (name = "phone")
    private String phone;
    @Column (name = "create_at")
    private LocalDateTime createAt;

    @Column (name = "update_at")
    private LocalDateTime updateAt;

    @Column (name = "address")
    private String address;
    @Column (name = "is_disable")
    private String isDisable;

    @ManyToOne
    @JoinColumn(name = "role_id") // Tên cột liên kết với RoleEntity
    private RoleEntity role; // Đảm bảo tên thuộc tính khớp với mappedBy

    @OneToMany
    private List<BookingEntity> booking;
}
