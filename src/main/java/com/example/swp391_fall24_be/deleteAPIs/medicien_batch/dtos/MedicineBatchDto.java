package com.example.swp391_fall24_be.deleteAPIs.medicien_batch.dtos;

import com.example.swp391_fall24_be.deleteAPIs.medicine.MedicineEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
public class MedicineBatchDto {
    private MedicineEntity medicineEntity;

    private int quantity;

    private LocalDateTime receivedAt;

    private LocalDate expirationDate;
}
