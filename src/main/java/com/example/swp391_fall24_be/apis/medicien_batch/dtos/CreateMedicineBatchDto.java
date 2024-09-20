package com.example.swp391_fall24_be.apis.medicien_batch.dtos;

import com.example.swp391_fall24_be.apis.medicien_batch.MedicineBatchEntity;
import com.example.swp391_fall24_be.apis.medicine.MedicineEntity;
import com.example.swp391_fall24_be.core.IDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateMedicineBatchDto implements IDto<MedicineBatchEntity> {

    @NotBlank(message = "Medicine ID cannot be null")
    @JsonProperty("medicineID")
    private String medicineID;

    @Positive(message = "Quantity must be a positive number")
    @JsonProperty("quantity")
    private int quantity;

    @NotNull(message = "Expiration date cannot be null")
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

        entity.setExpirationDate(expirationDate);
        return entity;
    }
}
