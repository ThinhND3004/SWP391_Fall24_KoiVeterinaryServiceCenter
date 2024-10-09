package com.example.swp391_fall24_be.deleteAPIs.medicien_batch.dtos;

import com.example.swp391_fall24_be.deleteAPIs.medicien_batch.MedicineBatchEntity;
import com.example.swp391_fall24_be.deleteAPIs.medicine.MedicineEntity;
import com.example.swp391_fall24_be.core.IDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UpdateMedicineBatchDto implements IDto<MedicineBatchEntity> {
    @NotBlank(message = "Medicine ID cannot be blank!")
    @JsonProperty("medicineID")
    private String medicineID;

    @Positive(message = "Quantity must be a positive number")
    @JsonProperty("quantity")
    private Integer quantity;

    @Future(message = "Expiration date must be in the future")
    @JsonProperty("expirationDate")
    private LocalDate expirationDate;


    @Override
    public MedicineBatchEntity toEntity() {
        MedicineBatchEntity entity = new MedicineBatchEntity();
        entity.setQuantity(quantity);

        MedicineEntity medicine = new MedicineEntity();
        medicine.setId(medicineID);
        entity.setMedicine(medicine);

        entity.setExpirationDate(expirationDate);;
        return entity;
    }
}
