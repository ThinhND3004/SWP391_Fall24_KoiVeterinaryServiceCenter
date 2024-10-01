package com.example.swp391_fall24_be.apis.services;

import com.example.swp391_fall24_be.apis.services.dtos.CreateServiceDto;
import com.example.swp391_fall24_be.apis.services.dtos.PaginateServiceDto;
import com.example.swp391_fall24_be.apis.services.dtos.ServiceDto;
import com.example.swp391_fall24_be.apis.services.dtos.UpdateServiceDto;
import com.example.swp391_fall24_be.core.AbstractController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services")
@Tag(name = "Services", description = "Management service in clinic.")
public class ServicesController extends AbstractController<
        ServiceEntity,
        String,
        CreateServiceDto,
        UpdateServiceDto,
        PaginateServiceDto,
        ServiceDto
        > {
}
