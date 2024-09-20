package com.example.swp391_fall24_be.apis.shipping.dtos;

import com.example.swp391_fall24_be.core.IDto;
import com.example.swp391_fall24_be.apis.shipping.ShippingEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class UpdateShippingDto implements IDto<ShippingEntity>  {
    @NotNull(message = "Price per meter is required")
    @Positive(message = "Price per meter must be a positive number")
    private Float pricePerMeters;

    @NotNull(message = "Update time is required")
    private LocalDateTime updateAt;

    @NotNull(message = "Create time is required")
    private LocalDateTime createAt;

    public UpdateShippingDto(ShippingEntity shippingEntity) {
        this.createAt = shippingEntity.getCreatedAt();
    }

    @Override
    public ShippingEntity toEntity() {
        ShippingEntity entity = new ShippingEntity();
        entity.setPricePerMeters(pricePerMeters);
        entity.setUpdatedAt(updateAt);
        entity.setCreatedAt(createAt);
        return entity;
    }
}