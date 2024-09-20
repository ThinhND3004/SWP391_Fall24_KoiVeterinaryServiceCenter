package com.example.swp391_fall24_be.apis.ponds.dto;

import com.example.swp391_fall24_be.core.AbstractPagination;
import com.example.swp391_fall24_be.apis.ponds.PondEntity;

public class PaginatePondDto extends AbstractPagination<PondEntity> {

    public PaginatePondDto(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public PondEntity toEntity() {
        return null;
    }
}
