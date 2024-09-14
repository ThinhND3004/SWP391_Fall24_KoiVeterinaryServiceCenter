package com.example.swp391_fall24_be.apis.accounts;

import com.example.swp391_fall24_be.apis.accounts.dtos.CreateAccountDto;
import com.example.swp391_fall24_be.apis.accounts.dtos.PaginateAccountDto;
import com.example.swp391_fall24_be.apis.accounts.dtos.UpdateAccountDto;
import com.example.swp391_fall24_be.apis.roles.Role;
import com.example.swp391_fall24_be.apis.roles.RoleRepository;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ErrorReport;
import com.example.swp391_fall24_be.core.ErrorEnum;
import com.example.swp391_fall24_be.core.ProjectException;
import com.example.swp391_fall24_be.utils.CryptoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountsService extends AbstractService<Account, String, CreateAccountDto, UpdateAccountDto, PaginateAccountDto> {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CryptoUtils cryptoUtils;
    @Override
    protected void beforeCreate(Account account) throws ProjectException {
        List<ErrorReport> errorList = new ArrayList<>();

        Optional<Role> findRoleResult = roleRepository.findByName(account.getRole().getName());
        if(findRoleResult.isEmpty()){
            errorList.add(new ErrorReport("AccountsService_beforeCreate", ErrorEnum.EntityNotFound,"Role not found!"));
        }
        else account.setRole(findRoleResult.get());

        if(account.getPassword() != null) {
            account.setPassword(cryptoUtils.crypto(account.getPassword()));
        }

        if(!errorList.isEmpty()){
            throw new ProjectException(errorList);
        }
    }

    @Override
    protected void beforeUpdate(Account oldEntity, Account newEntity) throws ProjectException {

    }

    @Override
    public Account delete(String id) throws ProjectException {
        return null;
    }
}
