package com.example.swp391_fall24_be.core;

import com.example.swp391_fall24_be.apis.bookings.DTOs.BookingDTO;
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
            return new ResponseDto<>(
                    HttpStatus.OK,
                    "Get many successfully!",
                    responseData,
                    null
            );
        }
        catch (Exception e){
            return new ResponseDto<>(
                    HttpStatus.BAD_REQUEST,
                    "Cannot get entities!",
                    null,
                    e.getMessage()
            );

        }
    }

    @Override
    public ResponseDto<ResponseType> doGet(@Parameter IdType id) {
        try {
            return new ResponseDto<>(
                    HttpStatus.OK,
                    "Get one successfully!",
                    service.findById(id).toResponseDto(),
                    null
            );
        }
        catch (Exception e){
            return new ResponseDto<>(
                    HttpStatus.BAD_REQUEST,
                    "Cannot get one entity!",
                    null,
                    e.getMessage()
            );
        }
    }

    @Override
    @ResponseBody
    public ResponseDto<ResponseType> doPost(@Valid @RequestBody CreateDto dto){
        try {
            return new ResponseDto<>(
                    HttpStatus.OK,
                    "Create successfully!",
                    service.create(dto).toResponseDto(),
                    null
            );
        }
        catch (Exception e){
            return new ResponseDto<>(
                    HttpStatus.BAD_REQUEST,
                    "Cannot create entity!",
                    null,
                    e.getMessage()
            );
        }
    }

    @Override
    public ResponseDto<ResponseType> doPut(@Parameter IdType id, @RequestBody UpdateDto dto) {
        try {
            return new ResponseDto<>(
                    HttpStatus.OK,
                    "Update successfully!",
                    service.update(id,dto).toResponseDto(),
                    null
            );
        }
        catch (Exception e){
            return new ResponseDto<>(
                    HttpStatus.BAD_REQUEST,
                    "Cannot update entity!",
                    null,
                    e.getMessage()
            );
        }
    }

    @Override
    public ResponseDto<ResponseType> doDelete(@Parameter IdType id) {
        try {
            return new ResponseDto<>(
                    HttpStatus.OK,
                    "Delete successfully!",
                    service.delete(id).toResponseDto(),
                    null
            );
        }
        catch (Exception e){
            return new ResponseDto<>(
                    HttpStatus.BAD_REQUEST,
                    "Cannot delete entity!",
                    null,
                    e.getMessage()
            );
        }
    }
}
