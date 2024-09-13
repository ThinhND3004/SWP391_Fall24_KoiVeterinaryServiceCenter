package com.example.swp391_fall24_be.core;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractController<EntityType extends IObject<ResponseType>, IdType, CreateDto extends IDto<EntityType>, UpdateDto extends IDto<EntityType>,PaginationDto extends AbstractPagination<EntityType>, ResponseType>
    implements IController<EntityType ,IdType, CreateDto, UpdateDto, PaginationDto, ResponseType>{
    protected AbstractService<EntityType, IdType, CreateDto, UpdateDto, PaginationDto> service;
    @Override
    public ResponseDto<List<ResponseType>> doGetMany(PaginationDto paginationDto) throws ProjectException {
        List<ResponseType> responseData = new ArrayList<>();
        try {
            List<EntityType> entityList = service.findAll(paginationDto);
            for (EntityType entity : entityList){
                responseData.add(entity.toResponseDto());
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
    public ResponseDto<ResponseType> doGet(IdType id) throws ProjectException {
        try {
            return new ResponseDto<>(
                    200,
                    "Get one successfully!",
                    service.findById(id).toResponseDto(),
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
    public ResponseDto<ResponseType> doPost(CreateDto dto) throws ProjectException {
        try {
            return new ResponseDto<>(
                    200,
                    "Create successfully!",
                    service.create(dto).toResponseDto(),
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
    public ResponseDto<ResponseType> doPut(IdType id, UpdateDto dto) throws ProjectException {
        try {
            return new ResponseDto<>(
                    200,
                    "Update successfully!",
                    service.update(id,dto).toResponseDto(),
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
    public ResponseDto<ResponseType> doDelete(IdType id) throws ProjectException {
        try {
            return new ResponseDto<>(
                        200,
                        "Delete successfully!",
                    service.delete(id).toResponseDto(),
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
