package com.example.swp391_fall24_be.apis.services.DTOs;

import com.example.swp391_fall24_be.apis.services.Service;
import com.example.swp391_fall24_be.core.AbstractPagination;

public class PaginateServiceDTO extends AbstractPagination<Service> {
    public PaginateServiceDTO(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public Service toEntity() {
        return null;
    }
}
