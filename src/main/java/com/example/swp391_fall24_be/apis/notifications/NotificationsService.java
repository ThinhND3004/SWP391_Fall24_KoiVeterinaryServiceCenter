package com.example.swp391_fall24_be.apis.notifications;

import com.example.swp391_fall24_be.apis.notifications.dtos.CreateNotificationDto;
import com.example.swp391_fall24_be.apis.notifications.dtos.PaginateNotificationDto;
import com.example.swp391_fall24_be.apis.notifications.dtos.UpdateNotificationDto;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.stereotype.Service;

@Service
public class NotificationsService extends AbstractService<
        NotificationEntity,
        Long,
        CreateNotificationDto,
        UpdateNotificationDto,
        PaginateNotificationDto
        > {
    @Override
    protected void beforeCreate(NotificationEntity entity) throws ProjectException {

    }

    @Override
    protected void beforeUpdate(NotificationEntity oldEntity, NotificationEntity newEntity) throws ProjectException {

    }

    @Override
    public NotificationEntity delete(Long id) throws ProjectException {
        return null;
    }
}
