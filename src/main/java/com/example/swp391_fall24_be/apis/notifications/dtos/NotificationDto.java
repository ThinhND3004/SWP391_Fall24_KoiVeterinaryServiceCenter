package com.example.swp391_fall24_be.apis.notifications.dtos;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.notifications.NotificationTypeEnum;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class NotificationDto {
    private String accountEmail;
    private String title;
    private String description;
    private NotificationTypeEnum type;
    private LocalDateTime readAt;
    private LocalDateTime createdAt;
}
