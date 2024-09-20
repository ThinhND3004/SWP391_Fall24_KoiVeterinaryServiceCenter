package com.example.swp391_fall24_be.apis.notifications.dtos;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class NotificationDto {
    private AccountEntity account;
    private String description;
    private LocalDateTime createdAt;
}
