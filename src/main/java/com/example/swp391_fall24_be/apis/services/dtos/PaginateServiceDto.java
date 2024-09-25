package com.example.swp391_fall24_be.apis.services.dtos;

import com.example.swp391_fall24_be.apis.services.ServiceEntity;
import com.example.swp391_fall24_be.core.AbstractPagination;

public class PaginateServiceDto extends AbstractPagination<ServiceEntity> {
    public PaginateServiceDto(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public ServiceEntity toEntity() {
        return null;
    }
}
