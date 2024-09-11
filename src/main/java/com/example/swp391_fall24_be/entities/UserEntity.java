package com.example.swp391_fall24_be.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "users")
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

    @JoinColumn(name = "role")
    @ManyToOne
    private RoleEntity role;

    @OneToMany
    private List<BookingEntity> booking;

    public List<BookingEntity> getBooking() {
        return booking;
    }

    public void setBooking(List<BookingEntity> booking) {
        this.booking = booking;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(String isDisable) {
        this.isDisable = isDisable;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }
}
