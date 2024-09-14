package com.example.swp391_fall24_be.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "users")
@Data
public class UserEntity {

    @Id
    private UUID id;

    @Column (name = "email", unique = true)
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

    @JoinColumn(name = "role")
    @ManyToOne
    private RoleEntity role;

    @OneToMany
    private List<BookingEntity> booking;


}
