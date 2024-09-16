package com.example.swp391_fall24_be.apis.treatments.dto;

import com.example.swp391_fall24_be.entities.KoiFishEntity;
import com.example.swp391_fall24_be.entities.PondEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TreatmentDto {
    private KoiFishEntity fishID;
    private PondEntity pondID;
    private UUID prescriptionID;
    private String diagnosis;
    private String notes;
    private LocalDateTime createdAt;
}
