package com.example.swp391_fall24_be.apis.koispecies.dto;

import com.example.swp391_fall24_be.core.AbstractPagination;
import com.example.swp391_fall24_be.apis.koispecies.KoiSpeciesEntity;

import java.beans.ConstructorProperties;

public class PaginateKoiSpeciesDto extends AbstractPagination<KoiSpeciesEntity> {

    @ConstructorProperties({"page", "unitPerPage"})
    public PaginateKoiSpeciesDto(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public KoiSpeciesEntity toEntity() {
        return new KoiSpeciesEntity();
    }
}
