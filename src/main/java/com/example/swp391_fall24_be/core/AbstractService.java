package com.example.swp391_fall24_be.core;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<
        EntityType,
        IdType,
        CreateDto extends IDto<EntityType>,
        UpdateDto extends IDto<EntityType>,
        PaginationDto extends AbstractPagination<EntityType>
> implements IService<EntityType, IdType, CreateDto, UpdateDto, PaginationDto>{
    protected JpaRepository<EntityType, IdType> repository;

    protected abstract void beforeCreate(EntityType entity) throws ProjectException;
    protected abstract void beforeUpdate(EntityType oldEntity, EntityType newEntity) throws ProjectException;
    public List<EntityType> findAll(PaginationDto dto){
        List<EntityType> entities;
        if(dto != null){
            entities = repository.findAll(Example.of(dto.toEntity()));
        }
        else entities = repository.findAll();

        return entities;
    }

    @Override
    public EntityType findById(IdType id) throws ProjectException {
        Optional<EntityType> findResult = repository.findById(id);
        if(findResult.isPresent()){
            return findResult.get();
        }
        throw new ProjectException(new ErrorReport("findById", ErrorType.EntityNotFound,"Entity not found!"));
    }

    @Override
    public EntityType create(CreateDto dto) throws ProjectException {
        EntityType entity = dto.toEntity();
        beforeCreate(entity);
        entity = repository.save(entity);
        return entity;
    }

    @Override
    public EntityType update(IdType id, UpdateDto dto) throws ProjectException {
        EntityType oldEntity = findById(id);
        EntityType updatedEntity = dto.toEntity();
        beforeUpdate(oldEntity, updatedEntity);
        oldEntity = repository.save(updatedEntity);
        return oldEntity;
    }
}
