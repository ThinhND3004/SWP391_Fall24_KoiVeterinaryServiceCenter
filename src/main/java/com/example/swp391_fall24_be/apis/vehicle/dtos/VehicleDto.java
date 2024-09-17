package com.example.swp391_fall24_be.apis.vehicle.dtos;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class VehicleDto{
    private String name;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
