package com.example.swp391_fall24_be.apis.treatments.dto;

import com.example.swp391_fall24_be.core.AbstractPagination;
import com.example.swp391_fall24_be.apis.treatments.Treatment;

public class PaginateTreatmentDto extends AbstractPagination<Treatment> {

    public PaginateTreatmentDto(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public Treatment toEntity() {
        return null;
    }
}
