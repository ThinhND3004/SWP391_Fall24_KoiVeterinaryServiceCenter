package com.example.swp391_fall24_be.apis.medicien_batch.dtos;

import com.example.swp391_fall24_be.apis.medicien_batch.MedicineBatchEntity;
import com.example.swp391_fall24_be.apis.medicine.MedicineEntity;
import com.example.swp391_fall24_be.core.IDto;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UpdateMedicineBatchDto implements IDto<MedicineBatchEntity> {

    private MedicineEntity medicineID;

    @Positive(message = "Quantity must be a positive number")
    private Integer quantity;

    @FutureOrPresent(message = "Received date must be now or in the future")
    private LocalDateTime receivedAt;

    @Future(message = "Expiration date must be in the future")
    private LocalDate expirationDate;


    @Override
    public MedicineBatchEntity toEntity() {
        MedicineBatchEntity entity = new MedicineBatchEntity();
        if (medicineID != null) {
            entity.setMedicineID(medicineID);
        }
        if (quantity != null) {
            entity.setQuantity(quantity);
        }
        if (receivedAt != null) {
            entity.setReceivedAt(receivedAt);
        }
        if (expirationDate != null) {
            entity.setExpirationDate(expirationDate);
        }
        return entity;
    }
}
