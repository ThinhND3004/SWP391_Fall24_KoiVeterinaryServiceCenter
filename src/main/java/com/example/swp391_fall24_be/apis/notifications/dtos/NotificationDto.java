package com.example.swp391_fall24_be.apis.notifications.dtos;

import com.example.swp391_fall24_be.apis.accounts.Account;
import com.example.swp391_fall24_be.apis.notifications.Notification;
import com.example.swp391_fall24_be.core.IDto;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class NotificationDto implements IDto<Notification> {
    private Account account;
    private String description;
    private LocalDateTime createdAt;


    @Override
    public Notification toEntity() {
        return null;
    }
}
