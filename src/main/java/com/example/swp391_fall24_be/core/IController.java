package com.example.swp391_fall24_be.core;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IController <EntityType extends IObject<ResponseType>,IdType, CreateDto extends IDto<EntityType>, UpdateDto extends IDto<EntityType>, PaginationDto extends AbstractPagination<EntityType>, ResponseType>{
    @GetMapping("/")
    ResponseDto<List<ResponseType>> doGetMany(PaginationDto paginationDto) throws ProjectException;
    @GetMapping("/{id}")
    ResponseDto<ResponseType> doGet(@PathVariable("id") IdType id) throws ProjectException;
    @PostMapping("/")
    ResponseDto<ResponseType> doPost(@Valid @RequestBody CreateDto dto) throws ProjectException;
    @PutMapping("/{id}")
    ResponseDto<ResponseType> doPut(@PathVariable("id") IdType id, @Valid @RequestBody UpdateDto dto) throws ProjectException;
    @DeleteMapping("/{id}")
    ResponseDto<ResponseType> doDelete(@PathVariable("id") IdType id) throws ProjectException;
}
