package com.example.swp391_fall24_be.apis.vehicle.dtos;

import com.example.swp391_fall24_be.apis.shipping.ShippingEntity;
import com.example.swp391_fall24_be.apis.vehicle.VehicleEntity;
import com.example.swp391_fall24_be.core.IDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UpdateVehicleDto implements IDto<VehicleEntity> {

    @NotBlank(message = "Vehicle's name is required!")
    private String name;

    @NotNull(message = "Update time is required!")
    private LocalDateTime updateTime;

    private List<ShippingEntity> shippingEntities;

    @NotNull(message = "Create time is required!")
    private LocalDateTime createTime;

    public UpdateVehicleDto(VehicleEntity oldVehicle) {
        this.createTime = oldVehicle.getCreateAt();
    }

    @Override
    public VehicleEntity toEntity() {
        VehicleEntity entity = new VehicleEntity();
        entity.setShippingEntities(shippingEntities);
        entity.setUpdateAt(updateTime);
        entity.setCreateAt(createTime);
        entity.setName(name);
        return entity;
    }
}
