package com.example.swp391_fall24_be.apis.treatments.dto;

import com.example.swp391_fall24_be.core.IDto;
import com.example.swp391_fall24_be.apis.koifishes.KoiFish;
import com.example.swp391_fall24_be.apis.ponds.Pond;
import com.example.swp391_fall24_be.apis.treatments.Treatment;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class CreateTreatmentDto implements IDto<Treatment> {

    @NotBlank(message = "Fish is required!")
    @JsonProperty("fishID")
    private KoiFish fishID;

    @NotBlank(message = "Pond is required!")
    @JsonProperty("pondID")
    private Pond pondID;

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
    public Treatment toEntity() {
        Treatment treatment = new Treatment();
        treatment.setFishID(fishID);
        treatment.setPondID(pondID);
        treatment.setPrescriptionID(prescriptionID);
        treatment.setDiagnosis(diagnosis);
        treatment.setNotes(notes);
        return treatment;
    }
}
