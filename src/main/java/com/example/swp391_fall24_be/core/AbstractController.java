package com.example.swp391_fall24_be.core;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractController<
        EntityType extends IObject<EntityType>,
        IdType,
        CreateDto extends IDto<EntityType>,
        UpdateDto extends IDto<EntityType>,
        PaginationDto extends AbstractPagination<EntityType>
> implements IController<EntityType, IdType, CreateDto, UpdateDto, PaginationDto> {
    protected AbstractService<EntityType, IdType, CreateDto, UpdateDto, PaginationDto> service;
    @Override
    public ResponseDto<List<EntityType>> doGetMany(PaginationDto paginationDto) throws ProjectException {
        List<EntityType> responseData = new ArrayList<>();
        try {
            List<EntityType> entityList = service.findAll(paginationDto);
            for (EntityType entity : entityList){
                responseData.add(entity.toResponseData());
            }
            return new ResponseDto<>(
                    200,
                    "Get many successfully!",
                    responseData,
                    null
            );
        }
        catch (Exception e){
            return new ResponseDto<>(
                    400,
                    "Cannot get entities!",
                    null,
                    e.getMessage()
            );
        }
    }

    @Override
    public ResponseDto<EntityType> doGet(IdType id) throws ProjectException {
        try {
            return new ResponseDto<>(
                    200,
                    "Get one successfully!",
                    service.findById(id).toResponseData(),
                    null
            );
        }
        catch (Exception e){
            return new ResponseDto<>(
                    400,
                    "Cannot get one entity!",
                    null,
                    e.getMessage()
            );
        }
    }

    @Override
    public ResponseDto<EntityType> doPost(CreateDto dto) throws ProjectException {
        try {
            return new ResponseDto<>(
                    200,
                    "Create successfully!",
                    service.create(dto).toResponseData(),
                    null
            );
        }
        catch (Exception e){
            return new ResponseDto<>(
                    400,
                    "Cannot create entity!",
                    null,
                    e.getMessage()
            );
        }
    }

    @Override
    public ResponseDto<EntityType> doPut(IdType id, UpdateDto dto) throws ProjectException {
        try {
            return new ResponseDto<>(
                    200,
                    "Update successfully!",
                    service.update(id,dto).toResponseData(),
                    null
            );
        }
        catch (Exception e){
            return new ResponseDto<>(
                    400,
                    "Cannot update entity!",
                    null,
                    e.getMessage()
            );
        }
    }

    @Override
    public ResponseDto<EntityType> doDelete(IdType id) throws ProjectException {
        try {
            return new ResponseDto<>(
                    200,
                    "Delete successfully!",
                    service.delete(id).toResponseData(),
                    null
            );
        }
        catch (Exception e){
            return new ResponseDto<>(
                    400,
                    "Cannot delete entity!",
                    null,
                    e.getMessage()
            );
        }
    }
}
