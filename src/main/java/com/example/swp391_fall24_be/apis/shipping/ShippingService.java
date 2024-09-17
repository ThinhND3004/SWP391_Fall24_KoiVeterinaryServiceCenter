package com.example.swp391_fall24_be.apis.shipping;

import com.example.swp391_fall24_be.apis.shipping.dtos.CreateShippingDto;
import com.example.swp391_fall24_be.apis.shipping.dtos.PaginateShippingDto;
import com.example.swp391_fall24_be.apis.shipping.dtos.UpdateShippingDto;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import com.example.swp391_fall24_be.entities.ShippingEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShippingService extends AbstractService<
        ShippingEntity,
        UUID,
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
    public ShippingEntity delete(UUID id) throws ProjectException {
        return null;
    }
}
