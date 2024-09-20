package com.example.swp391_fall24_be.apis.ponds.dto;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PondDto {
    private AccountEntity customerID;
    private String name;
    private String location;
    private float sizeSquareMeters;
    private float depthMeters;
    private String waterType;
    private float temperatureCelsius;
    private float pHLevel;
    private LocalDateTime lastMaintenanceDate;
    private LocalDateTime dateCreated;
}
