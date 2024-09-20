package com.example.swp391_fall24_be.apis.accounts.dtos;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.core.AbstractPagination;

public class PaginateAccountDto extends AbstractPagination<AccountEntity> {

    public PaginateAccountDto(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public AccountEntity toEntity() {
        return null;
    }
}
