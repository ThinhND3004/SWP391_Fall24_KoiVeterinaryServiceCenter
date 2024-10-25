package com.example.swp391_fall24_be.apis.ponds.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PondDto {
    private String name;
    private float sizeSquareMeters;
    private float depthMeters;
    private String waterType;
    private float temperatureCelsius;
    private float pHLevel;
    private LocalDate lastMaintenanceDate;
    private LocalDateTime dateCreated;
}
