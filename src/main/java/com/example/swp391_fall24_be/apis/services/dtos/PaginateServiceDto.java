package com.example.swp391_fall24_be.apis.services.dtos;

import com.example.swp391_fall24_be.apis.services.ServiceEntity;
import com.example.swp391_fall24_be.core.AbstractPagination;

import java.beans.ConstructorProperties;

public class PaginateServiceDto extends AbstractPagination<ServiceEntity> {
    public Boolean isDisable;
    @ConstructorProperties({"page", "unitPerPage", "isDisable"})
    public PaginateServiceDto(Integer page, Integer unitPerPage, Boolean isDisable) {

        super(page, unitPerPage);
        this.isDisable = isDisable;
    }

    @Override
    public ServiceEntity toEntity() {
        ServiceEntity service = new ServiceEntity();
        service.setIsDisable(isDisable);

        return service;
    }
}
