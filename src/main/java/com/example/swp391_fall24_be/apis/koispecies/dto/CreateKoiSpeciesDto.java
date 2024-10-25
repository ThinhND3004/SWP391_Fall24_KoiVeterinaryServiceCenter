package com.example.swp391_fall24_be.apis.koispecies.dto;

import com.example.swp391_fall24_be.core.IDto;
import com.example.swp391_fall24_be.apis.koispecies.KoiSpeciesEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateKoiSpeciesDto implements IDto<KoiSpeciesEntity> {

    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name must not exceed 50 characters")
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(max = 200, message = "Description must not exceed 50 characters")
    @JsonProperty("description")
    private String description;

    @Override
    public KoiSpeciesEntity toEntity() {
        KoiSpeciesEntity entity = new KoiSpeciesEntity();
        entity.setName(name);
        entity.setDescription(description);
        return entity;
    }
}
