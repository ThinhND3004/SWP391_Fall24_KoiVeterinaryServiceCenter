package com.example.swp391_fall24_be.dto;

import com.example.swp391_fall24_be.core.AbstractPagination;
import com.example.swp391_fall24_be.entities.UserEntity;

public class AccountListDto extends AbstractPagination<UserEntity> {

    public AccountListDto(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public UserEntity toEntity() {
        return null;
    }
}
