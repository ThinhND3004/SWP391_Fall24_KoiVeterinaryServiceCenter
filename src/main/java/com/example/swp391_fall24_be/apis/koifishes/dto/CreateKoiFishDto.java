package com.example.swp391_fall24_be.apis.koifishes.dto;

import com.example.swp391_fall24_be.apis.accounts.Account;
import com.example.swp391_fall24_be.core.IDto;
import com.example.swp391_fall24_be.apis.koifishes.KoiFish;
import com.example.swp391_fall24_be.apis.koispecies.KoiSpecies;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

public class CreateKoiFishDto implements IDto<KoiFish> {
    @JsonProperty("customer_id")
    @NotBlank(message = "customerID is required")
    private Account customerID;

    @JsonProperty("species_id")
    @NotBlank(message = "speciesID is required")
    private KoiSpecies speciesID;

    @JsonProperty("name")
    @NotBlank(message = "name is required")
    @Size(max = 50, message = "name must not exceed 50 characters")
    private String name;

    @JsonProperty("color")
    @NotBlank(message = "color is required")
    @Size(max = 50, message = "color must not exceed 50 characters")
    private String color;

    @JsonProperty("length")
    @Range(min = 0, message = "length must be greater than 0")
    private float length;

    @JsonProperty("width")
    @Range(min = 0, message = "width must be greater than 0")
    private float width;

    @JsonProperty("age_years")
    @Range(min = 0, message = "ageYears must be greater than 0")
    private int ageYears;

    @Override
    public KoiFish toEntity() {
        KoiFish entity = new KoiFish();
        entity.setCustomerID(customerID);
        entity.setSpeciesID(speciesID);
        entity.setName(name);
        entity.setColor(color);
        entity.setLength(length);
        entity.setWidth(width);
        entity.setAgeYears(ageYears);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }
}
