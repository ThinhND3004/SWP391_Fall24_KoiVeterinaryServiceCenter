package com.example.swp391_fall24_be.apis.reports.dto;

import com.example.swp391_fall24_be.apis.koispecies.KoiSpeciesEntity;
import com.example.swp391_fall24_be.apis.prescription.PrescriptionEntity;
import com.example.swp391_fall24_be.core.IDto;
import com.example.swp391_fall24_be.apis.ponds.PondEntity;
import com.example.swp391_fall24_be.apis.reports.ReportEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class CreateReportDto implements IDto<ReportEntity> {

    @NotBlank(message = "Koi species ID is required!")
    @JsonProperty("koiSpeciesID")
    private String koiSpeciesID;

    @NotBlank(message = "Prescription is required!")
    @JsonProperty("prescriptionID")
    private String prescriptionID;

    @NotBlank(message = "Diagnosis is required!")
    @JsonProperty("diagnosis")
    private String diagnosis;

    @JsonProperty("notes")
    private String notes;

    @Override
    public ReportEntity toEntity() {
        ReportEntity report = new ReportEntity();

        KoiSpeciesEntity koiSpecies = new KoiSpeciesEntity();
        koiSpecies.setId(koiSpeciesID);
        report.setKoiSpecies(koiSpecies);

        PrescriptionEntity prescription = new PrescriptionEntity();
        prescription.setId(prescriptionID);
        report.setPrescription(prescription);

        report.setDiagnosis(diagnosis);
        report.setNotes(notes);
        return report;
    }
}
