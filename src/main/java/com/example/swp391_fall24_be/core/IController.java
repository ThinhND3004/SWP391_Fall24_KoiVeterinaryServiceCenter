package com.example.swp391_fall24_be.core;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IController <EntityType extends IObject<EntityType>,IdType, CreateDto extends IDto<EntityType>, UpdateDto extends IDto<EntityType>, PaginationDto extends AbstractPagination<EntityType>>{
    @GetMapping("/")
    ResponseDto<List<EntityType>> doGetMany(PaginationDto paginationDto) throws ProjectException;
    @GetMapping("/{id}")
    ResponseDto<EntityType> doGet(@PathVariable("id") IdType id) throws ProjectException;
    @PostMapping("/")
    ResponseDto<EntityType> doPost(@Valid @RequestBody CreateDto dto) throws ProjectException;
    @PutMapping("/{id}")
    ResponseDto<EntityType> doPut(@PathVariable("id") IdType id, @Valid @RequestBody UpdateDto dto) throws ProjectException;
    @DeleteMapping("/{id}")
    ResponseDto<EntityType> doDelete(@PathVariable("id") IdType id) throws ProjectException;
}
