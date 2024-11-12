package com.example.swp391_fall24_be.apis.notifications;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.notifications.dtos.CreateNotificationDto;
import com.example.swp391_fall24_be.apis.notifications.dtos.NotificationDto;
import com.example.swp391_fall24_be.apis.notifications.dtos.PaginateNotificationDto;
import com.example.swp391_fall24_be.apis.notifications.dtos.UpdateNotificationDto;
import com.example.swp391_fall24_be.core.AbstractController;
import com.example.swp391_fall24_be.core.ResponseDto;
import com.example.swp391_fall24_be.security.JwtProvider;
import com.example.swp391_fall24_be.utils.AuthUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    private final JwtProvider jwtProvider;
    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationsService notificationsService;

    public NotificationsController(JwtProvider jwtProvider, NotificationsService notificationsService, SimpMessagingTemplate messagingTemplate) {
        this.jwtProvider = jwtProvider;
        this.notificationsService = notificationsService;
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("/current")
    public ResponseDto<List<NotificationDto>> getCurrent(){
        try {
            AccountEntity account = AuthUtils.getCurrentAccount();
            return new ResponseDto<>(
                    HttpStatus.OK.value(),
                    "Get notifications from token success!",
                    notificationsService.findAllByAccount(account),
                    null
            );
        }
        catch (Exception e){
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseDto<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "Cannot get notifications from token!",
                    null,
                    errors
            );
        }
    }

    @GetMapping("/is-sent/{accountEmail}/{bookingId}")
    public ResponseDto<Boolean> checkIsSent(
            @PathVariable String accountEmail,
            @PathVariable("bookingId") String bookingId
    ){
        try {
            return new ResponseDto<>(
                    HttpStatus.OK.value(),
                    "Check is notification sent successful!",
                    notificationsService.findByAccount(accountEmail, bookingId),
                    null
            );
        }
        catch (Exception e){
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseDto<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "Check is notification sent failed!",
                    null,
                    errors
            );
        }
    }

    @MessageMapping("/send-email")
    public ResponseDto<NotificationDto> doMessageOnEmail(CreateNotificationDto dto) {
        try {
            AccountEntity sender = jwtProvider.verifyToken(dto.getToken());
            dto.setSender(sender);
            NotificationDto notificationDto = notificationsService.create(dto).toResponseDto();
            notificationDto.setMessage(dto.getMessage());
            notificationDto.setNotiType(dto.getNotiType());
            String accountEmail = notificationDto.getAccountEmail();

            messagingTemplate.convertAndSend("/topic/notifications/" + accountEmail, new ResponseDto<>(
                    HttpStatus.OK.value(),
                    "Create notification success!",
                    notificationDto,
                    null
            ));

        }
        catch (Exception e){
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseDto<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "Create notification failed!",
                    null,
                    errors
            );
        }
        // Call your service to create and retrieve the NotificationDto
        AccountEntity sender = jwtProvider.verifyToken(dto.getToken());
        dto.setSender(sender);
        ResponseDto<NotificationDto> response = super.doPost(dto);


        return response;
    }

    @MessageMapping("/send")
    @SendTo("/topic/notifications")
    public ResponseDto<NotificationDto> doMessage(CreateNotificationDto dto) {

        try {
            AccountEntity sender = jwtProvider.verifyToken(dto.getToken());
            dto.setSender(sender);
            NotificationDto notificationDto = notificationsService.create(dto).toResponseDto();
            notificationDto.setMessage(dto.getMessage());
            notificationDto.setNotiType(dto.getNotiType());

            return new ResponseDto<>(
                    HttpStatus.OK.value(),
                    "Create notification success!",
                    notificationDto,
                    null
            );
        }
        catch (Exception e){
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseDto<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "Create notification failed!",
                    null,
                    errors
            );
        }
    }
}
