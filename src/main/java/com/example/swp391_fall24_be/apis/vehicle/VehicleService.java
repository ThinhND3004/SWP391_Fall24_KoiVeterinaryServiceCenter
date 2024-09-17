package com.example.swp391_fall24_be.apis.vehicle;

import com.example.swp391_fall24_be.apis.vehicle.dtos.CreateVehicleDto;
import com.example.swp391_fall24_be.apis.vehicle.dtos.PaginateVehicleDto;
import com.example.swp391_fall24_be.apis.vehicle.dtos.UpdateVehicleDto;
import com.example.swp391_fall24_be.apis.vehicle.dtos.VehicleDto;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VehicleService extends AbstractService<
        VehicleEntity,
        UUID,
        CreateVehicleDto,
        UpdateVehicleDto,
        PaginateVehicleDto
> {
    @Override
    protected void beforeCreate(VehicleEntity entity) throws ProjectException {

    }

    @Override
    protected void beforeUpdate(VehicleEntity oldEntity, VehicleEntity newEntity) throws ProjectException {

    }

    @Override
    public VehicleEntity delete(UUID id) throws ProjectException {
        return null;
    }
}
