package com.example.swp391_fall24_be.apis.koispecies.dto;

import com.example.swp391_fall24_be.core.IDto;
import com.example.swp391_fall24_be.apis.koispecies.KoiSpecies;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
public class UpdateKoiSpeciesDto implements IDto<KoiSpecies> {

    @NotBlank(message = "name is required")
    @Size(max = 50, message = "name must not exceed 50 characters")
    @JsonProperty("name")
    private String name;

    @Override
    public KoiSpecies toEntity() {
        KoiSpecies entity = new KoiSpecies();
        entity.setName(name);
        return entity;
    }
}
