package com.example.swp391_fall24_be.apis.medicine.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MedicineDto {
    private String name;
    private String description;
    private String manufacturer;
    private float price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
//    private List<PrescriptionMedicine> prescriptionMedicines;
}
