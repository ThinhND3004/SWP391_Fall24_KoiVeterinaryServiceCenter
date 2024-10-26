package com.example.swp391_fall24_be.apis.notifications.dtos;

import com.example.swp391_fall24_be.apis.notifications.NotificationEntity;
import com.example.swp391_fall24_be.core.IDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class UpdateNotificationDto implements IDto<NotificationEntity> {

    @JsonProperty("readAt")
    private LocalDateTime readAt;

    @Override
    public NotificationEntity toEntity() {
        NotificationEntity notification = new NotificationEntity();
        notification.setReadAt(readAt);
        return notification;
    }
}
