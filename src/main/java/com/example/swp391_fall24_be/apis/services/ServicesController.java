package com.example.swp391_fall24_be.apis.services;

import com.example.swp391_fall24_be.apis.services.dtos.CreateServiceDto;
import com.example.swp391_fall24_be.apis.services.dtos.PaginateServiceDto;
import com.example.swp391_fall24_be.apis.services.dtos.ServiceDto;
import com.example.swp391_fall24_be.apis.services.dtos.UpdateServiceDto;
import com.example.swp391_fall24_be.core.AbstractController;
import com.example.swp391_fall24_be.core.ResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/services")
@Tag(name = "Services", description = "Management service in clinic.")
@CrossOrigin
public class ServicesController extends AbstractController<
        ServiceEntity,
        String,
        CreateServiceDto,
        UpdateServiceDto,
        PaginateServiceDto,
        ServiceDto
        > {

    @Autowired
    private ServicesService servicesService;
    @GetMapping("/getAllService")
    public ResponseDto<?> getAllService()
    {
        List<ServiceEntity> resultList = servicesService.getAllServiceEntity();

        return new ResponseDto<>(
            HttpStatus.OK.value(),
            "Get current service success!",
                resultList,
            null
    );
    }


}
