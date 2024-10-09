package com.example.swp391_fall24_be.deleteAPIs.prescription.dtos;

import com.example.swp391_fall24_be.deleteAPIs.prescription.PrescriptionEntity;
import com.example.swp391_fall24_be.core.AbstractPagination;

public class PaginatePrescriptionDto extends AbstractPagination<PrescriptionEntity> {
    public PaginatePrescriptionDto(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public PrescriptionEntity toEntity() {
        return null;
    }
}
