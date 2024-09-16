package com.example.swp391_fall24_be.apis.koifishes.dto;

import com.example.swp391_fall24_be.apis.accounts.Account;
import com.example.swp391_fall24_be.entities.KoiSpeciesEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class KoiFishDto {
    private Account customerID;
    private KoiSpeciesEntity speciesID;
    private String name;
    private String color;
    private float length;
    private float width;
    private int ageYears;
    private LocalDateTime createdAt;
}
