package com.example.swp391_fall24_be.apis.shipping;

import com.example.swp391_fall24_be.apis.shipping.dtos.CreateShippingDto;
import com.example.swp391_fall24_be.apis.shipping.dtos.PaginateShippingDto;
import com.example.swp391_fall24_be.apis.shipping.dtos.ShippingDto;
import com.example.swp391_fall24_be.apis.shipping.dtos.UpdateShippingDto;
import com.example.swp391_fall24_be.core.AbstractController;

import java.util.UUID;

public class ShippingControlller extends AbstractController <
        ShippingEntity,
        UUID,
        CreateShippingDto,
        UpdateShippingDto,
        PaginateShippingDto,
        ShippingDto
        > {
}
