package com.example.swp391_fall24_be.core;

import java.util.List;

public interface IService <EntityType, IdType, CreateDto extends IDto<EntityType>, UpdateDto extends IDto<EntityType>, PaginationDto extends AbstractPagination<EntityType>>{
    List<EntityType> findAll(PaginationDto dto);
    EntityType findById(IdType id) throws ProjectException;
    EntityType create(CreateDto dto) throws ProjectException;
    EntityType delete(IdType id) throws ProjectException;
    EntityType update(IdType id, UpdateDto dto) throws ProjectException;
}
