package com.example.swp391_fall24_be.apis.services;

import com.example.swp391_fall24_be.apis.services.DTOs.CreateServiceDTO;
import com.example.swp391_fall24_be.apis.services.DTOs.PaginateServiceDTO;
import com.example.swp391_fall24_be.apis.services.DTOs.ServiceDTO;
import com.example.swp391_fall24_be.apis.services.DTOs.UpdateServiceDTO;
import com.example.swp391_fall24_be.core.AbstractController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services")
@Tag(name = "Services", description = "Service APIs")
public class ServiceController extends AbstractController<Service, String, CreateServiceDTO, UpdateServiceDTO, PaginateServiceDTO, ServiceDTO> {
}
