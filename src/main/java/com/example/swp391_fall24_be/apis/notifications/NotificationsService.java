package com.example.swp391_fall24_be.apis.notifications;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.accounts.AccountsRepository;
import com.example.swp391_fall24_be.apis.notifications.dtos.CreateNotificationDto;
import com.example.swp391_fall24_be.apis.notifications.dtos.NotificationDto;
import com.example.swp391_fall24_be.apis.notifications.dtos.PaginateNotificationDto;
import com.example.swp391_fall24_be.apis.notifications.dtos.UpdateNotificationDto;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import com.example.swp391_fall24_be.utils.AuthUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    private final NotificationsRepository notificationsRepository;

    public NotificationsService(AccountsRepository accountsRepository, NotificationsRepository notificationsRepository) {
        this.accountsRepository = accountsRepository;
        this.notificationsRepository = notificationsRepository;
    }

    @Override
    protected void beforeCreate(NotificationEntity entity) throws ProjectException {
        if(entity.getAccount() != null){
            String email = entity.getAccount().getEmail();
            Optional<AccountEntity> findAccountByEmailResult = accountsRepository.findByEmail(email);
            if(findAccountByEmailResult.isEmpty()){
                throw new Error("Cannot find account with email " + email);
            }
            entity.setAccount(findAccountByEmailResult.get());
        }
    }

    @Override
    protected void beforeUpdate(NotificationEntity oldEntity, NotificationEntity newEntity) throws ProjectException {

    }

    @Override
    public NotificationEntity delete(Long id) throws ProjectException {
        NotificationEntity entity = notificationsRepository.findById(id)
                .orElseThrow(() -> new Error("Notification not found"));
        notificationsRepository.deleteById(id);
        return entity;
    }

    public Boolean findByAccount(String accountEmail, String bookingId){
        Optional<NotificationEntity> findResult = notificationsRepository.findByAccountEmailAndBookingId(accountEmail,bookingId);
        return findResult.isPresent();
    }

    public List<NotificationDto> findAllByAccount(AccountEntity account){
        List<NotificationEntity> notificationEntityList = notificationsRepository.findAllByAccount(account);
        List<NotificationDto> dtoList = new ArrayList<>();
        for(NotificationEntity notification : notificationEntityList){
            dtoList.add(notification.toResponseDto());
        }
        return dtoList;
    }
}
