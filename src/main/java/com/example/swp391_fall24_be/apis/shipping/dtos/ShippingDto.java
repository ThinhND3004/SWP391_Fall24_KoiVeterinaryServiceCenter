package com.example.swp391_fall24_be.apis.shipping.dtos;

import com.example.swp391_fall24_be.core.IDto;
import com.example.swp391_fall24_be.entities.ShippingEntity;

import java.time.LocalDateTime;

public class ShippingDto{
    private String vehicle;

    private float pricePerMeters;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

}
