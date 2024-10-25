package com.example.swp391_fall24_be.core;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


public abstract class AbstractController<
        EntityType extends IObject<ResponseType>,
        IdType,
        CreateDto extends IDto<EntityType>,
        UpdateDto extends IDto<EntityType>,
        PaginationDto extends AbstractPagination<EntityType>,
        ResponseType
> implements IController<EntityType, IdType, CreateDto, UpdateDto, PaginationDto, ResponseType> {

    @Autowired
    protected AbstractService<EntityType, IdType, CreateDto, UpdateDto, PaginationDto> service;
    @Override
    @Operation(summary = "Get many with filter")
    @ApiResponse
    public ResponseDto<List<ResponseType>> doGetMany(@Valid PaginationDto paginationDto) {
        List<ResponseType> responseData = new ArrayList<>();
        try {
            List<EntityType> entityList = service.findAll(paginationDto);
            for (EntityType entity : entityList){
                responseData.add(entity.toResponseDto());
            }
            if (responseData.isEmpty())
            {
                return new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "Get many failed!",
                        responseData,
                        null
                );
            } else {
                return new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "Get many successfully!",
                        responseData,
                        null
                );
            }


        }
        catch (Exception e){
            List<String> errorList = new ArrayList<>();
            errorList.add(e.getMessage());
            return new ResponseDto<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "Cannot get entities!",
                    null,
                    errorList
            );

        }
    }

    @Override
    public ResponseDto<ResponseType> doGet(@Parameter IdType id) {
        try {
            return new ResponseDto<>(
                    HttpStatus.OK.value(),
                    "Get one successfully!",
                    service.findById(id).toResponseDto(),
                    null
            );
        }
        catch (Exception e){
            List<String> errorList = new ArrayList<>();
            errorList.add(e.getMessage());
            return new ResponseDto<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "Cannot get one entity!",
                    null,
                    errorList
            );
        }
    }

    @Override
    @ResponseBody
    public ResponseDto<ResponseType> doPost(@Valid @RequestBody CreateDto dto){
        try {
            return new ResponseDto<>(
                    HttpStatus.OK.value(),
                    "Create successfully!",
                    service.create(dto).toResponseDto(),
                    null
            );
        }
        catch (Exception e){
            List<String> errorList = new ArrayList<>();
            errorList.add(e.getMessage());
            return new ResponseDto<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "Cannot create entity!",
                    null,
                    errorList
            );
        }
    }

    @Override
    public ResponseDto<ResponseType> doPut(@Parameter IdType id, @Valid @RequestBody UpdateDto dto) {
        try {
            return new ResponseDto<>(
                    HttpStatus.OK.value(),
                    "Update successfully!",
                    service.update(id,dto).toResponseDto(),
                    null
            );
        }
        catch (Exception e){
            List<String> errorList = new ArrayList<>();
            errorList.add(e.getMessage());
            return new ResponseDto<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "Cannot update entity!",
                    null,
                    errorList
            );
        }
    }

    @Override
    public ResponseDto<ResponseType> doDelete(@Parameter IdType id) {
        try {
            return new ResponseDto<>(
                    HttpStatus.OK.value(),
                    "Delete successfully!",
                    service.delete(id).toResponseDto(),
                    null
            );
        }
        catch (Exception e){
            List<String> errorList = new ArrayList<>();
            errorList.add(e.getMessage());
            return new ResponseDto<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "Cannot delete entity!",
                    null,
                    errorList
            );
        }
    }
}
