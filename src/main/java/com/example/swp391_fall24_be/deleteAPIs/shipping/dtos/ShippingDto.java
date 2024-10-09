package com.example.swp391_fall24_be.deleteAPIs.shipping.dtos;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ShippingDto{

    private float pricePerMeters;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

}
