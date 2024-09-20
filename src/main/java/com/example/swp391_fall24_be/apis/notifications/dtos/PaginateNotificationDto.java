package com.example.swp391_fall24_be.apis.notifications.dtos;

import com.example.swp391_fall24_be.apis.notifications.NotificationEntity;
import com.example.swp391_fall24_be.core.AbstractPagination;

public class PaginateNotificationDto extends AbstractPagination<NotificationEntity> {
    public PaginateNotificationDto(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public NotificationEntity toEntity() {
        return null;
    }
}
