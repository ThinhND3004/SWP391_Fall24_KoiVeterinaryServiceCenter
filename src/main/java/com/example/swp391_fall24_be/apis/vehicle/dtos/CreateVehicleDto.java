package com.example.swp391_fall24_be.apis.vehicle.dtos;

import com.example.swp391_fall24_be.apis.shipping.ShippingEntity;
import com.example.swp391_fall24_be.apis.vehicle.VehicleEntity;
import com.example.swp391_fall24_be.core.IDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class CreateVehicleDto implements IDto<VehicleEntity> {
    @NotBlank(message = "Vehicle's name is required!")
    private String name;

    @NotBlank(message = "Create time is required!")
    private LocalDateTime createAt;

    private List<ShippingEntity> shippingEntities;
    @Override
    public VehicleEntity toEntity() {
        VehicleEntity entity = new VehicleEntity();
        entity.setCreateAt(createAt);
        entity.setUpdateAt(null);
        entity.setShippingEntities(shippingEntities);
        entity.setName(name);
        return entity;
    }
}
