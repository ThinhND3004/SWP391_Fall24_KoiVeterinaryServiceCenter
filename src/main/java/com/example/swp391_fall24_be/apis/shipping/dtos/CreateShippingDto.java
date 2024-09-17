package com.example.swp391_fall24_be.apis.shipping.dtos;

import com.example.swp391_fall24_be.core.IDto;
import com.example.swp391_fall24_be.apis.shipping.ShippingEntity;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class CreateShippingDto implements IDto<ShippingEntity> {

    @NotBlank(message = "Vehicle is required!")
    private String vehicle;

    @NotBlank(message = "Price per meter is required!")
    private float pricePerMeters;


    @NotBlank(message = "Create time is required!")
    private LocalDateTime createAt;


    @Override
    public ShippingEntity toEntity() {
        ShippingEntity entity = new ShippingEntity();
        entity.setVehicle(vehicle);
        entity.setPricePerMeters(pricePerMeters);
        entity.setUpdatedAt(null);
        entity.setCreatedAt(createAt);
        return entity;
    }
}
