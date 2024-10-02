package com.example.swp391_fall24_be.apis.services;

import com.example.swp391_fall24_be.apis.services.dtos.CreateServiceDto;
import com.example.swp391_fall24_be.apis.services.dtos.PaginateServiceDto;
import com.example.swp391_fall24_be.apis.services.dtos.UpdateServiceDto;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.stereotype.Service;

@Service
public class ServicesService extends AbstractService<
        ServiceEntity,
        String,
        CreateServiceDto,
        UpdateServiceDto,
        PaginateServiceDto
        > {
    @Override
    protected void beforeCreate(ServiceEntity entity) throws ProjectException {

    }

    @Override
    protected void beforeUpdate(ServiceEntity oldEntity, ServiceEntity newEntity) throws ProjectException {

    }

    @Override
    public ServiceEntity delete(String id) throws ProjectException {
        return null;
    }
}
