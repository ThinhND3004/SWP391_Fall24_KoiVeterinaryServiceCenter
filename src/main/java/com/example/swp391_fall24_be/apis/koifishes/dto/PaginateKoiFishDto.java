package com.example.swp391_fall24_be.apis.koifishes.dto;

import com.example.swp391_fall24_be.core.AbstractPagination;
import com.example.swp391_fall24_be.entities.KoiFishEntity;

public class PaginateKoiFishDto extends AbstractPagination<KoiFishEntity> {

    public PaginateKoiFishDto(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public KoiFishEntity toEntity() {
        return null;
    }
}
