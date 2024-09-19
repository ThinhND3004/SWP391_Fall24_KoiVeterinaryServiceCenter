package com.example.swp391_fall24_be.apis.images.dtos;

import com.example.swp391_fall24_be.apis.images.Image;
import com.example.swp391_fall24_be.core.AbstractPagination;

public class PaginateImageDto extends AbstractPagination<Image> {
    public PaginateImageDto(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public Image toEntity() {
        return null;
    }
}
