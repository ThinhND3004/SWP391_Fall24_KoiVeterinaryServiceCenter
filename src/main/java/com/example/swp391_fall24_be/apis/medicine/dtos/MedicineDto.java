package com.example.swp391_fall24_be.apis.medicine.dtos;

import com.example.swp391_fall24_be.apis.prescription.PrescriptionEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
@Getter
@Setter
public class MedicineDto {
    private String id;
    private String name;
    private String description;
    private String manufacturer;
    private float price;
    private LocalDateTime createdAt;
    private Set<PrescriptionEntity> prescriptionEntities;
    private String mediImage_id;
}
