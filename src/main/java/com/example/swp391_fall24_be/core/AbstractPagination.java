package com.example.swp391_fall24_be.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public abstract class AbstractPagination<EntityType> implements IDto<EntityType>  {
    protected int page;
    protected int unitPerPage;

    public Pageable getPage(){
        return PageRequest.of(page, unitPerPage);
    }
    public Pageable getSortedPage(String sortedValue, Sort.Direction directionEnum){
        return PageRequest.of(page,unitPerPage, Sort.by(directionEnum,sortedValue));
    }

    public AbstractPagination(Integer page, Integer unitPerPage) {
        this.page = page != null ? page : 1;
        this.unitPerPage = unitPerPage != null ? unitPerPage : 10;
    }
}
