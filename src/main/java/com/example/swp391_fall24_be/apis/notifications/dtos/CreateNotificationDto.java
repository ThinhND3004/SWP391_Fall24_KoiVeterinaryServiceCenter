package com.example.swp391_fall24_be.apis.notifications.dtos;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.notifications.NotificationEntity;
import com.example.swp391_fall24_be.apis.notifications.NotificationTypeEnum;
import com.example.swp391_fall24_be.core.IDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateNotificationDto implements IDto<NotificationEntity> {
    @NotBlank(message = "Account Id must not be blank!")
    @JsonProperty("accountId")
    private AccountEntity accountId;

    @NotBlank(message = "Title must not be blank!")
    @Size(max = 200, message = "Title must not pass 200 letters!")
    @JsonProperty("title")
    private String title;

    @NotBlank(message = "Description must not be blank!")
    @Size(max = 200, message = "Description must not pass 200 letters!")
    @JsonProperty("description")
    private String description;

    @NotNull(message = "Type must not be null!")
    @JsonProperty("type")
    private NotificationTypeEnum type;

    @Override
    public NotificationEntity toEntity() {
        NotificationEntity notification = new NotificationEntity();
        notification.setAccount(accountId);
        notification.setTitle(title);
        notification.setDescription(description);
        notification.setType(type);
        return notification;
    }
}
