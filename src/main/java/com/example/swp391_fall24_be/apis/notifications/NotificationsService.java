package com.example.swp391_fall24_be.apis.notifications;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.accounts.AccountsRepository;
import com.example.swp391_fall24_be.apis.notifications.dtos.CreateNotificationDto;
import com.example.swp391_fall24_be.apis.notifications.dtos.PaginateNotificationDto;
import com.example.swp391_fall24_be.apis.notifications.dtos.UpdateNotificationDto;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationsService extends AbstractService<
        NotificationEntity,
        Long,
        CreateNotificationDto,
        UpdateNotificationDto,
        PaginateNotificationDto
        > {
    private final AccountsRepository accountsRepository;

    public NotificationsService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }


    @Override
    protected void beforeCreate(NotificationEntity entity) throws ProjectException {
        String email = entity.getAccount().getEmail();
        Optional<AccountEntity> findAccountByEmailResult = accountsRepository.findByEmail(email);
        if(findAccountByEmailResult.isEmpty()){
            throw new Error("Cannot find account with email " + email);
        }
        entity.setAccount(findAccountByEmailResult.get());
    }

    @Override
    protected void beforeUpdate(NotificationEntity oldEntity, NotificationEntity newEntity) throws ProjectException {

    }

    @Override
    public NotificationEntity delete(Long id) throws ProjectException {
        return null;
    }
}
