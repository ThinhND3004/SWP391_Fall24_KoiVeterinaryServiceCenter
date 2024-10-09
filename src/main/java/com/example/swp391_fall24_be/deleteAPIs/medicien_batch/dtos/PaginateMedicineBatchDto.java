package com.example.swp391_fall24_be.deleteAPIs.medicien_batch.dtos;

import com.example.swp391_fall24_be.deleteAPIs.medicien_batch.MedicineBatchEntity;
import com.example.swp391_fall24_be.core.AbstractPagination;

public class PaginateMedicineBatchDto extends AbstractPagination<MedicineBatchEntity> {
    public PaginateMedicineBatchDto(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public MedicineBatchEntity toEntity() {
        return null;
    }
}
