package com.example.swp391_fall24_be.apis.shipping.dtos;

import com.example.swp391_fall24_be.apis.vehicle.VehicleEntity;
import com.example.swp391_fall24_be.core.IDto;
import com.example.swp391_fall24_be.apis.shipping.ShippingEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class CreateShippingDto implements IDto<ShippingEntity> {

    @NotNull(message = "Vehicle is required!")
    private VehicleEntity vehicle;

    @NotNull(message = "Price per meter is required!")
    @Positive(message = "Price per meter must be a positive number!")
    private Float pricePerMeters;

    @NotNull(message = "Create time is required!")
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
