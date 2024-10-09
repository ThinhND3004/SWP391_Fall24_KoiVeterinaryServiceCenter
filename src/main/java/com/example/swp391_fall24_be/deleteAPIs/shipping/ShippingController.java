package com.example.swp391_fall24_be.deleteAPIs.shipping;

import com.example.swp391_fall24_be.deleteAPIs.shipping.dtos.CreateShippingDto;
import com.example.swp391_fall24_be.deleteAPIs.shipping.dtos.PaginateShippingDto;
import com.example.swp391_fall24_be.deleteAPIs.shipping.dtos.ShippingDto;
import com.example.swp391_fall24_be.deleteAPIs.shipping.dtos.UpdateShippingDto;
import com.example.swp391_fall24_be.core.AbstractController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shipping")
@Tag(name = "Shipping", description = "Shipping APIs")
public class ShippingController extends AbstractController <
        ShippingEntity,
        String,
        CreateShippingDto,
        UpdateShippingDto,
        PaginateShippingDto,
        ShippingDto
        > {
}
