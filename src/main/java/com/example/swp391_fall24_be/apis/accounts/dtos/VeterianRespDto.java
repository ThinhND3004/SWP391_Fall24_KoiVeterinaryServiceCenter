package com.example.swp391_fall24_be.apis.accounts.dtos;

import com.example.swp391_fall24_be.apis.accounts.AccountRoleEnum;
import com.example.swp391_fall24_be.apis.profiles.ProfileEntity;
import com.example.swp391_fall24_be.sub_class.TimeSlot;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class VeterianRespDto {
    private String email;
    private String fullName;
    private LocalDate dob;
    private String phone;
    private String address;

    private AccountRoleEnum role;
    private ProfileEntity profile;

    private List<TimeSlot> timeSlot;
}
