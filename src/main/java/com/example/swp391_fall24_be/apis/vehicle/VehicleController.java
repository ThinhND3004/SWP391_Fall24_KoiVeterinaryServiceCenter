package com.example.swp391_fall24_be.apis.vehicle;

import com.example.swp391_fall24_be.apis.vehicle.dtos.CreateVehicleDto;
import com.example.swp391_fall24_be.apis.vehicle.dtos.PaginateVehicleDto;
import com.example.swp391_fall24_be.apis.vehicle.dtos.UpdateVehicleDto;
import com.example.swp391_fall24_be.apis.vehicle.dtos.VehicleDto;
import com.example.swp391_fall24_be.core.AbstractController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
@RestController
@RequestMapping("/vehicle")
@Tag(name = "Vehicles", description = "Vehicle APIs")
public class VehicleController extends AbstractController<
        VehicleEntity,
        UUID,
        CreateVehicleDto,
        UpdateVehicleDto,
        PaginateVehicleDto,
        VehicleDto
> {



}
