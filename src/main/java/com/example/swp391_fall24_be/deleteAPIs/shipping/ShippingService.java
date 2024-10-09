package com.example.swp391_fall24_be.deleteAPIs.shipping;

import com.example.swp391_fall24_be.deleteAPIs.shipping.dtos.CreateShippingDto;
import com.example.swp391_fall24_be.deleteAPIs.shipping.dtos.PaginateShippingDto;
import com.example.swp391_fall24_be.deleteAPIs.shipping.dtos.UpdateShippingDto;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.stereotype.Service;

@Service
public class ShippingService extends AbstractService<
        ShippingEntity,
        String,
        CreateShippingDto,
        UpdateShippingDto,
        PaginateShippingDto
> {
    @Override
    protected void beforeCreate(ShippingEntity entity) throws ProjectException {

    }

    @Override
    protected void beforeUpdate(ShippingEntity oldEntity, ShippingEntity newEntity) throws ProjectException {

    }

    @Override
    public ShippingEntity delete(String id) throws ProjectException {
        return null;
    }
}
