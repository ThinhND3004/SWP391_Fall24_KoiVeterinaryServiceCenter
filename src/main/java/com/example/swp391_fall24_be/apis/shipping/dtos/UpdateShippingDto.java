package com.example.swp391_fall24_be.apis.shipping.dtos;

import com.example.swp391_fall24_be.core.IDto;
import com.example.swp391_fall24_be.entities.ShippingEntity;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class UpdateShippingDto implements IDto<ShippingEntity> {
    @NotBlank(message = "Vehicle is required")
    private String vehicle;
    @NotBlank(message = "Price per meter is required")
    private float pricePerMeters;
    @NotBlank(message = "Update time is required")
    private LocalDateTime updateAt;
    @NotBlank(message = "Create time is required")
    private LocalDateTime createAt;

    public UpdateShippingDto(ShippingEntity shippingEntity) {
        createAt = shippingEntity.getCreatedAt();
    }

    @Override
    public ShippingEntity toEntity() {
        ShippingEntity entity = new ShippingEntity();
        entity.setVehicle(vehicle);
        entity.setPricePerMeters(pricePerMeters);
        entity.setUpdatedAt(updateAt);
        entity.setCreatedAt(createAt);
        return entity;
    }
}
