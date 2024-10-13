package com.example.swp391_fall24_be.apis.notifications;

import com.example.swp391_fall24_be.apis.notifications.dtos.CreateNotificationDto;
import com.example.swp391_fall24_be.apis.notifications.dtos.NotificationDto;
import com.example.swp391_fall24_be.apis.notifications.dtos.PaginateNotificationDto;
import com.example.swp391_fall24_be.apis.notifications.dtos.UpdateNotificationDto;
import com.example.swp391_fall24_be.core.AbstractController;
import com.example.swp391_fall24_be.core.ResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
@Tag(name = "Notification", description = "Send notification to account.")
public class NotificationsController extends AbstractController<
        NotificationEntity,
        Long,
        CreateNotificationDto,
        UpdateNotificationDto,
        PaginateNotificationDto,
        NotificationDto
        > {

    @MessageMapping("/send")
    @SendTo("/topic/notifications")
    public ResponseDto<NotificationDto> doMessage(CreateNotificationDto dto) {
        return super.doPost(dto);
    }
}
