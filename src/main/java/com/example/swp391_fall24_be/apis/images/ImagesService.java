package com.example.swp391_fall24_be.apis.images;

import com.example.swp391_fall24_be.apis.images.dtos.CreateImageDto;
import com.example.swp391_fall24_be.apis.images.dtos.PaginateImageDto;
import com.example.swp391_fall24_be.apis.images.dtos.UpdateImageDto;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ImagesService extends AbstractService<
        ImageEntity,
        String,
        CreateImageDto,
        UpdateImageDto,
        PaginateImageDto
        > {
    @Override
    protected void beforeCreate(ImageEntity entity) throws ProjectException {

    }

    @Override
    protected void beforeUpdate(ImageEntity oldEntity, ImageEntity newEntity) throws ProjectException {

    }

    @Override
    public ImageEntity delete(String id) throws ProjectException {
        return null;
    }
}
