package com.example.swp391_fall24_be.service;

import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import com.example.swp391_fall24_be.dto.AccountListDto;
import com.example.swp391_fall24_be.dto.AccountCreateDto;
import com.example.swp391_fall24_be.dto.AccountLoginResponseDto;
import com.example.swp391_fall24_be.dto.AccountResponseDto;
import com.example.swp391_fall24_be.dto.AccountUpdateDto;
import com.example.swp391_fall24_be.entities.UserEntity;
import com.example.swp391_fall24_be.repository.UserRepository;
import com.example.swp391_fall24_be.security.JwtProvider;
import com.example.swp391_fall24_be.utils.CryptoUtils;
import com.example.swp391_fall24_be.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService extends AbstractService<UserEntity, UUID, AccountCreateDto, AccountUpdateDto, AccountListDto> {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    protected UserRepository repository;

    @Autowired
    private CryptoUtils cryptoService;

    @Override
    protected void beforeCreate(UserEntity entity) throws ProjectException {
        Optional<UserEntity> check = repository.findByEmail(entity.getEmail());
        if (check.isPresent()) {
//            throw new ProjectException(new ErrorReport("beforeCreate", ErrorType.ValidationError,"Email is already in use"));
        }
    }

    @Override
    protected void beforeUpdate(UserEntity oldEntity, UserEntity newEntity) throws ProjectException {
        Optional<UserEntity> check = repository.findByEmail(newEntity.getEmail());
        if (check.isPresent() && !check.get().getId().equals(oldEntity.getId())) {
//            throw new ProjectException(new ErrorReport("beforeUpdate", ErrorType.ValidationError,"Email is already in use"));
        }
    }

    @Override
    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public UserEntity update(AccountUpdateDto dto) throws ProjectException {
//        repository.findByEmail(dto.getEmail()).orElseThrow(() -> new ProjectException(new ErrorReport("update", ErrorType.EntityNotFound, "Account not found")));
        UserEntity entity = dto.toEntity();
        return repository.save(entity);
    }

    @Override
    public UserEntity delete(UUID id) throws ProjectException {
//        UserEntity entity = repository.findById(id).orElseThrow(() -> new ProjectException(new ErrorReport("delete", ErrorType.EntityNotFound, "Account not found")));
        UserEntity entity = repository.findById(id).orElse(null);
        if (entity == null) {
            return null;
        }
        repository.delete(entity);
        return entity;
    }

}
