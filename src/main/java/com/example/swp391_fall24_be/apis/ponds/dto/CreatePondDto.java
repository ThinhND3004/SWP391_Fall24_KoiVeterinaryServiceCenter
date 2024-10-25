package com.example.swp391_fall24_be.apis.ponds.dto;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.core.IDto;
import com.example.swp391_fall24_be.apis.ponds.PondEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreatePondDto implements IDto<PondEntity> {
    @NotBlank(message = "Customer is required!")
    @JsonProperty("customer_id")
    private String customerID;

    @NotBlank(message = "Name is required!")
    @Size(max = 50, message = "Length of name must not exceed 50 letters!")
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "Size is required!")
    @JsonProperty("size_square_meters")
    @Range(min = 0, message = "Size must be greater than 0!")
    private float sizeSquareMeters;

    @NotBlank(message = "Depth is required!")
    @JsonProperty("depth_meters")
    @Range(min = 0, message = "Depth must be greater than 0!")
    private float depthMeters;

    @NotBlank(message = "Water type is required!")
    @JsonProperty("water_type")
    private String waterType;

    @NotBlank(message = "Temperature is required!")
    @JsonProperty("temperature_celsius")
    private float temperatureCelsius;

    @NotBlank(message = "pH level is required!")
    @JsonProperty("pH_level")
    @Range(min = 0, max = 14, message = "pH level must be between 0 and 14!")
    private float pHLevel;

    @JsonProperty("last_maintenance_date")
    private LocalDate lastMaintenanceDate;

    @Override
    public PondEntity toEntity() {
        PondEntity entity = new PondEntity();

        AccountEntity customer = new AccountEntity();
        customer.setId(customerID);
        entity.setCustomer(customer);

        entity.setName(name);
        entity.setSizeSquareMeters(sizeSquareMeters);
        entity.setDepthMeters(depthMeters);
        entity.setWaterType(waterType);
        entity.setTemperatureCelsius(temperatureCelsius);
        entity.setPHLevel(pHLevel);
        entity.setLastMaintenanceDate(lastMaintenanceDate);
        return entity;
    }
}
