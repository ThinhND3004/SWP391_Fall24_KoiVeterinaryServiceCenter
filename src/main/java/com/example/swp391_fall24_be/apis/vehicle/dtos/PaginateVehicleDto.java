package com.example.swp391_fall24_be.apis.vehicle.dtos;

import com.example.swp391_fall24_be.apis.vehicle.VehicleEntity;
import com.example.swp391_fall24_be.core.AbstractPagination;

public class PaginateVehicleDto extends AbstractPagination<VehicleEntity> {
    public PaginateVehicleDto(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public VehicleEntity toEntity() {
        return null;
    }
}
