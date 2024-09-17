package com.example.swp391_fall24_be.apis.koispecies.dto;

import com.example.swp391_fall24_be.core.AbstractPagination;
import com.example.swp391_fall24_be.apis.koispecies.KoiSpecies;

public class PaginateKoiSpeciesDto extends AbstractPagination<KoiSpecies> {

    public PaginateKoiSpeciesDto(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public KoiSpecies toEntity() {
        return null;
    }
}
