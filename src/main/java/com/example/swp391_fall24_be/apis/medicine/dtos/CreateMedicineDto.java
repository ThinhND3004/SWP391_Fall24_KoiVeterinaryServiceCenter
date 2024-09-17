package com.example.swp391_fall24_be.apis.medicine.dtos;

import com.example.swp391_fall24_be.apis.medicine.MedicineEntity;
import com.example.swp391_fall24_be.apis.prescription.PrescriptionEntity;
import com.example.swp391_fall24_be.core.IDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CreateMedicineDto implements IDto<MedicineEntity> {

    @NotBlank(message = "Medicine's name is required!")
    private String name;

    @NotBlank(message = "Description is required!")
    private String description;

    @NotBlank(message = "Manufacturer is required!")
    private String manufacturer;

    @NotNull(message = "Price is required!")
    @Positive(message = "Price must be a positive number!")
    private Float price;

    @NotNull(message = "Create time is required!")
    private LocalDateTime createAt;

    @Override
    public MedicineEntity toEntity() {
        MedicineEntity entity = new MedicineEntity();
        entity.setCreatedAt(createAt);
        entity.setPrescriptionEntities(new HashSet<>());
        entity.setName(name);
        entity.setPrice(price);
        entity.setDescription(description);
        entity.setManufacturer(manufacturer);

        return entity;
    }
}
