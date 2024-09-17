package com.example.swp391_fall24_be.apis.shipping.dtos;

import com.example.swp391_fall24_be.core.AbstractPagination;
import com.example.swp391_fall24_be.apis.shipping.ShippingEntity;

public class PaginateShippingDto extends AbstractPagination<ShippingEntity> {
    public PaginateShippingDto(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public ShippingEntity toEntity() {
        return null;
    }
}
