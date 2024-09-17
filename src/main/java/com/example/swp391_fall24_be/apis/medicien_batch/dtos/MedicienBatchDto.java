package com.example.swp391_fall24_be.apis.medicien_batch.dtos;

import com.example.swp391_fall24_be.apis.medicine.MedicineEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
public class MedicienBatchDto {
    private MedicineEntity medicineEntity;

    private int quantity;

    private LocalDateTime receivedAt;

    private LocalDate expirationDate;
}
