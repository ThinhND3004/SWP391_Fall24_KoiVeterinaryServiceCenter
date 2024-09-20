package com.example.swp391_fall24_be.apis.medicine.dtos;

import com.example.swp391_fall24_be.apis.medicine.MedicineEntity;
import com.example.swp391_fall24_be.apis.prescription.PrescriptionEntity;
import com.example.swp391_fall24_be.core.IDto;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "Description is required!")
    @JsonProperty("description")
    private String description;

    @NotBlank(message = "Manufacturer is required!")
    @JsonProperty("manufacturer")
    private String manufacturer;

    @NotNull(message = "Price is required!")
    @Positive(message = "Price must be a positive number!")
    @JsonProperty("price")
    private Float price;

    @Override
    public MedicineEntity toEntity() {
        MedicineEntity entity = new MedicineEntity();
        entity.setPrescriptionEntities(new HashSet<>());
        entity.setName(name);
        entity.setPrice(price);
        entity.setDescription(description);
        entity.setManufacturer(manufacturer);

        return entity;
    }
}
