package com.example.swp391_fall24_be.apis.services;

import com.example.swp391_fall24_be.apis.services.dtos.CreateServiceDto;
import com.example.swp391_fall24_be.apis.services.dtos.PaginateServiceDto;
import com.example.swp391_fall24_be.apis.services.dtos.ServiceDto;
import com.example.swp391_fall24_be.apis.services.dtos.UpdateServiceDto;
import com.example.swp391_fall24_be.core.AbstractController;

public class ServicesController extends AbstractController<
        ServiceEntity,
        String,
        CreateServiceDto,
        UpdateServiceDto,
        PaginateServiceDto,
        ServiceDto
        > {
}
