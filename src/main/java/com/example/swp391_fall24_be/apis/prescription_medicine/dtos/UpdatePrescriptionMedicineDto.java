package com.example.swp391_fall24_be.apis.prescription_medicine.dtos;

import com.example.swp391_fall24_be.apis.prescription_medicine.PrescriptionMedicine;
import com.example.swp391_fall24_be.core.IDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Range;

public class UpdatePrescriptionMedicineDto implements IDto<PrescriptionMedicine> {
    @Range(min = 1, message = "Amount must bigger than 0!")
    @JsonProperty("amount")
    private int amount;

    @Override
    public PrescriptionMedicine toEntity() {
        PrescriptionMedicine prescriptionMedicine = new PrescriptionMedicine();
        prescriptionMedicine.setAmount(amount);

        return prescriptionMedicine;
    }
}
