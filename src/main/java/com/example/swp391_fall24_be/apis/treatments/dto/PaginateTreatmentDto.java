package com.example.swp391_fall24_be.apis.treatments.dto;

import com.example.swp391_fall24_be.core.AbstractPagination;
import com.example.swp391_fall24_be.entities.TreatmentEntity;

public class PaginateTreatmentDto extends AbstractPagination<TreatmentEntity> {

    public PaginateTreatmentDto(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public TreatmentEntity toEntity() {
        return null;
    }
}
