package com.example.swp391_fall24_be.apis.reports.dtos;

import com.example.swp391_fall24_be.apis.koispecies.KoiSpeciesEntity;
import com.example.swp391_fall24_be.apis.koispecies.dto.KoiSpeciesDto;
import com.example.swp391_fall24_be.apis.ponds.dto.PondDto;
import com.example.swp391_fall24_be.apis.prescription.PrescriptionEntity;
import com.example.swp391_fall24_be.apis.prescription.dtos.PrescriptionDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReportDto {
    private List<KoiSpeciesDto> koiSpeciesDtoList;
    private PondDto pondDto;
    private PrescriptionDto prescriptionDto;
    private String diagnosis;
    private String notes;
    private LocalDateTime createdAt;
}
