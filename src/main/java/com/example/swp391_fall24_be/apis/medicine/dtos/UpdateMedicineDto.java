package com.example.swp391_fall24_be.apis.medicine.dtos;

import com.example.swp391_fall24_be.apis.medicine.MedicineEntity;
import com.example.swp391_fall24_be.apis.prescription.PrescriptionEntity;
import com.example.swp391_fall24_be.core.IDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class UpdateMedicineDto implements IDto<MedicineEntity> {
    @NotBlank(message = "Name is required!")
    private String name;
    @NotBlank(message = "Description is required!")
    private String description;
    @NotBlank(message = "Manufacturer is required!")
    private String manufacturer;

    @Positive(message = "Price must be a positive number!")
    private float price;

    @NotNull(message = "Create time is required!")
    private LocalDateTime createAt;

    private Set<PrescriptionEntity> prescriptionEntities;

    public UpdateMedicineDto(MedicineEntity oldEntity) {
        this.prescriptionEntities = oldEntity.getPrescriptionEntities();
        this.createAt = oldEntity.getCreatedAt();
    }

    @Override
    public MedicineEntity toEntity() {
        MedicineEntity entity = new MedicineEntity();

        if (name != null) {
            entity.setName(name);
        }
            entity.setPrice(price);
        if (description != null) {
            entity.setDescription(description);
        }
        if (manufacturer != null) {
            entity.setManufacturer(manufacturer);
        }
        entity.setCreatedAt(createAt);
        entity.setPrescriptionEntities(prescriptionEntities);
        return entity;
    }
}
