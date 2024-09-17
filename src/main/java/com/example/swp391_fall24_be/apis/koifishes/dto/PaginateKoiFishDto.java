package com.example.swp391_fall24_be.apis.koifishes.dto;

import com.example.swp391_fall24_be.core.AbstractPagination;
import com.example.swp391_fall24_be.apis.koifishes.KoiFish;

public class PaginateKoiFishDto extends AbstractPagination<KoiFish> {

    public PaginateKoiFishDto(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public KoiFish toEntity() {
        return null;
    }
}
