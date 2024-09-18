package com.example.swp391_fall24_be.apis.ponds.dto;

import com.example.swp391_fall24_be.core.AbstractPagination;
import com.example.swp391_fall24_be.apis.ponds.Pond;

public class PaginatePondDto extends AbstractPagination<Pond> {

    public PaginatePondDto(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public Pond toEntity() {
        return null;
    }
}
