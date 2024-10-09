package com.example.swp391_fall24_be.deleteAPIs.prescription_medicine.dtos;

import com.example.swp391_fall24_be.deleteAPIs.medicine.MedicineEntity;
import com.example.swp391_fall24_be.deleteAPIs.prescription.PrescriptionEntity;
import com.example.swp391_fall24_be.deleteAPIs.prescription_medicine.PrescriptionMedicine;
import com.example.swp391_fall24_be.core.IDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class CreatePrescriptionMedicineDto implements IDto<PrescriptionMedicine> {
    @NotBlank(message = "Prescription Id must not be blank")
    @JsonProperty("prescriptionId")
    private String prescriptionId;

    @NotBlank(message = "Prescription Id must not be blank")
    @JsonProperty("medicineId")
    private String medicineId;

    @Range(min = 1, message = "Amount must bigger than 0!")
    @JsonProperty("amount")
    private int amount;

    @Override
    public PrescriptionMedicine toEntity() {
        PrescriptionMedicine entity = new PrescriptionMedicine();
        entity.setAmount(amount);

        MedicineEntity medicine = new MedicineEntity();
        medicine.setId(medicineId);
        entity.setMedicine(medicine);

        PrescriptionEntity prescription = new PrescriptionEntity();
        prescription.setId(prescriptionId);
        entity.setPrescription(prescription);
        return entity;
    }
}
