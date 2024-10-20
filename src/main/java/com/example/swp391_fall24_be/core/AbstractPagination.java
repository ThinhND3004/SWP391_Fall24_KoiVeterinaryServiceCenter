package com.example.swp391_fall24_be.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public abstract class AbstractPagination<EntityType> implements IDto<EntityType>  {
    protected int page;
    protected int unitPerPage;

    @JsonIgnore
    public PageRequest getPageRequest(){
        return PageRequest.of(page, unitPerPage);
    }

    public Pageable getSortedPage(String sortedValue, Sort.Direction directionEnum){
        return PageRequest.of(page,unitPerPage, Sort.by(directionEnum,sortedValue));
    }

    public AbstractPagination(Integer page, Integer unitPerPage) {
        this.page = page != null ? page-1  : 0;
        this.unitPerPage = unitPerPage != null ? unitPerPage : 10;
    }
}
