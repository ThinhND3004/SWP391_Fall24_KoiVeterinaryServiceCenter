package com.example.swp391_fall24_be.apis.notifications.dtos;

import com.example.swp391_fall24_be.apis.notifications.NotificationEntity;
import com.example.swp391_fall24_be.core.IDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateNotificationDto implements IDto<NotificationEntity> {
    @NotBlank(message = "Description must not be blank!")
    @Size(max = 200, message = "Description must not pass 200 letters!")
    @JsonProperty("description")
    private String description;

    @Override
    public NotificationEntity toEntity() {
        NotificationEntity notification = new NotificationEntity();
        notification.setDescription(description);
        return notification;
    }
}