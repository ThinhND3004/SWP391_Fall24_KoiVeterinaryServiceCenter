package com.example.swp391_fall24_be.apis.accounts;

import com.example.swp391_fall24_be.apis.accounts.dtos.CreateAccountDto;
import com.example.swp391_fall24_be.apis.accounts.dtos.PaginateAccountDto;
import com.example.swp391_fall24_be.apis.accounts.dtos.UpdateAccountDto;
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
    private CryptoUtils cryptoUtils;
    @Autowired
    private AccountsRepository accountsRepository;

    @Override
    protected void beforeCreate(Account account) throws ProjectException {
        List<ErrorReport> errorList = new ArrayList<>();

        Optional<Account> findEmailResult = accountsRepository.findByEmail(account.getEmail());
        if(findEmailResult.isPresent()){
            errorList.add(new ErrorReport("AccountsService_beforeCreate", ErrorEnum.FieldDuplicated,"This email has been registered!"));
        }

        Optional<Account> findPhoneResult = accountsRepository.findByEmail(account.getEmail());
        if(findPhoneResult.isPresent()){
            errorList.add(new ErrorReport("AccountsService_beforeCreate", ErrorEnum.FieldDuplicated,"This phone has been registered!"));
        }

        if(account.getPassword() != null && errorList.isEmpty()) {
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
