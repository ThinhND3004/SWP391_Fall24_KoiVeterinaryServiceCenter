package com.example.swp391_fall24_be.apis.prescription.dtos;

import com.example.swp391_fall24_be.apis.prescription.PrescriptionStatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PrescriptionDto {
//    private MedicineEntity medicine;
    private Integer amount;
    private Float totalPrice;
    private PrescriptionStatusEnum prescriptionStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
//    private Set<PrescriptionMedicine> prescriptionMedicineSet;
//    private ReportEntity report;
//    private Transaction transaction;
}
