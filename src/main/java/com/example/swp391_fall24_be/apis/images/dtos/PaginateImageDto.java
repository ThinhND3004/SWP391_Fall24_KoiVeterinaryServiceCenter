package com.example.swp391_fall24_be.apis.images.dtos;

import com.example.swp391_fall24_be.apis.images.ImageEntity;
import com.example.swp391_fall24_be.core.AbstractPagination;

public class PaginateImageDto extends AbstractPagination<ImageEntity> {
    public PaginateImageDto(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public ImageEntity toEntity() {
        return null;
    }
}
