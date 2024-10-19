package com.example.swp391_fall24_be.apis.accounts.dtos;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.accounts.AccountRoleEnum;
import com.example.swp391_fall24_be.core.AbstractPagination;

import java.beans.ConstructorProperties;

public class PaginateAccountDto extends AbstractPagination<AccountEntity> {
    public AccountRoleEnum role;

    @ConstructorProperties({"page", "unitPerPage", "role"})
    public PaginateAccountDto(Integer page, Integer unitPerPage, AccountRoleEnum role) {
        super(page, unitPerPage);
        this.role = role;
    }

    @Override
    public AccountEntity toEntity() {
        AccountEntity account = new AccountEntity();
        account.setRole(role);
        return account;
    }
}
