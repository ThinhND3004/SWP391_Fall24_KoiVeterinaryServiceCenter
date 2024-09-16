package com.example.swp391_fall24_be.apis.treatments.dto;

import com.example.swp391_fall24_be.core.IDto;
import com.example.swp391_fall24_be.entities.KoiFishEntity;
import com.example.swp391_fall24_be.entities.PondEntity;
import com.example.swp391_fall24_be.entities.TreatmentEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class UpdateTreatmentDto implements IDto<TreatmentEntity> {
    @NotBlank(message = "Fish is required!")
    @JsonProperty("fishID")
    private KoiFishEntity fishID;

    @NotBlank(message = "Pond is required!")
    @JsonProperty("pondID")
    private PondEntity pondID;

    @NotBlank(message = "Prescription is required!")
    @JsonProperty("prescriptionID")
    @org.hibernate.validator.constraints.UUID
    private UUID prescriptionID;

    @NotBlank(message = "Diagnosis is required!")
    @JsonProperty("diagnosis")
    private String diagnosis;

    @JsonProperty("notes")
    private String notes;

    @Override
    public TreatmentEntity toEntity() {
        TreatmentEntity treatment = new TreatmentEntity();
        treatment.setFishID(fishID);
        treatment.setPondID(pondID);
        treatment.setPrescriptionID(prescriptionID);
        treatment.setDiagnosis(diagnosis);
        treatment.setNotes(notes);
        return treatment;
    }
}
