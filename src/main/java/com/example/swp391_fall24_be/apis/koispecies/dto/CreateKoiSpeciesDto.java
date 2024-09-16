package com.example.swp391_fall24_be.apis.koispecies.dto;

import com.example.swp391_fall24_be.apis.accounts.Account;
import com.example.swp391_fall24_be.core.IDto;
import com.example.swp391_fall24_be.entities.KoiSpeciesEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateKoiSpeciesDto implements IDto<KoiSpeciesEntity> {

    @NotBlank(message = "customerID is required")
    @JsonProperty("customer_id")
    private Account customerID;

    @NotBlank(message = "name is required")
    @Size(max = 50, message = "name must not exceed 50 characters")
    @JsonProperty("name")
    private String name;

    @Override
    public KoiSpeciesEntity toEntity() {
        KoiSpeciesEntity entity = new KoiSpeciesEntity();
        entity.setCustomerID(customerID);
        entity.setName(name);
        return entity;
    }
}
