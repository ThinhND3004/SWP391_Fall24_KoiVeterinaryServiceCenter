package com.example.swp391_fall24_be.apis.treatments.dto;

import com.example.swp391_fall24_be.apis.koifishes.KoiFish;
import com.example.swp391_fall24_be.apis.ponds.Pond;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TreatmentDto {
    private KoiFish fishID;
    private Pond pondID;
    private UUID prescriptionID;
    private String diagnosis;
    private String notes;
    private LocalDateTime createdAt;
}
