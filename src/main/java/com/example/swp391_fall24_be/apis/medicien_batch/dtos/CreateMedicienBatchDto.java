package com.example.swp391_fall24_be.apis.medicien_batch.dtos;

import com.example.swp391_fall24_be.apis.medicien_batch.MedicineBatchEntity;
import com.example.swp391_fall24_be.apis.medicine.MedicineEntity;
import com.example.swp391_fall24_be.core.IDto;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreateMedicienBatchDto implements IDto<MedicineBatchEntity> {

    @NotNull(message = "Medicine ID cannot be null")
    private MedicineEntity medicineID;

    @Positive(message = "Quantity must be a positive number")
    private int quantity;

    @NotNull(message = "Received date cannot be null")
    private LocalDateTime receivedAt;

    @NotNull(message = "Expiration date cannot be null")
    @Future(message = "Expiration date must be in the future")
    private LocalDate expirationDate;

    @Override
    public MedicineBatchEntity toEntity() {
        MedicineBatchEntity entity = new MedicineBatchEntity();
        entity.setQuantity(quantity);
        entity.setReceivedAt(receivedAt);
        entity.setMedicineID(medicineID);
        entity.setExpirationDate(expirationDate);
        return entity;
    }
}
