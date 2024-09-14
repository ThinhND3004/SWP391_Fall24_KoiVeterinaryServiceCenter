package com.example.swp391_fall24_be.apis.accounts.dtos;

import com.example.swp391_fall24_be.apis.accounts.Account;
import com.example.swp391_fall24_be.core.AbstractPagination;

public class PaginateAccountDto extends AbstractPagination<Account> {

    public PaginateAccountDto(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public Account toEntity() {
        return null;
    }
}
