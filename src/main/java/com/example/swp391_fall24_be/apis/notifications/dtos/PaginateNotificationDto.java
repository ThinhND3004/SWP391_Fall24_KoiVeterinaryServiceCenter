package com.example.swp391_fall24_be.apis.notifications.dtos;

import com.example.swp391_fall24_be.apis.notifications.Notification;
import com.example.swp391_fall24_be.core.AbstractPagination;

public class PaginateNotificationDto extends AbstractPagination<Notification> {
    public PaginateNotificationDto(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public Notification toEntity() {
        return null;
    }
}
