package com.example.swp391_fall24_be.apis.accounts;

import com.example.swp391_fall24_be.apis.accounts.dtos.CreateAccountDto;
import com.example.swp391_fall24_be.apis.accounts.dtos.PaginateAccountDto;
import com.example.swp391_fall24_be.apis.accounts.dtos.UpdateAccountDto;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class AccountsService extends AbstractService<Account, String, CreateAccountDto, UpdateAccountDto, PaginateAccountDto> {
    @Override
    protected void beforeCreate(Account entity) throws ProjectException {

    }

    @Override
    protected void beforeUpdate(Account oldEntity, Account newEntity) throws ProjectException {

    }

    @Override
    public Account delete(String id) throws ProjectException {
        return null;
    }
}
