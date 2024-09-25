package com.example.swp391_fall24_be.apis.reports.dto;

import com.example.swp391_fall24_be.apis.koispecies.KoiSpeciesEntity;
import com.example.swp391_fall24_be.apis.ponds.PondEntity;
import com.example.swp391_fall24_be.apis.prescription.PrescriptionEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReportDto {
    private KoiSpeciesEntity koiSpecies;
    private PondEntity pond;
    private PrescriptionEntity prescription;
    private String diagnosis;
    private String notes;
    private LocalDateTime createdAt;
}
