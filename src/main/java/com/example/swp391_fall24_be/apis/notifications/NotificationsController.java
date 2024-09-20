package com.example.swp391_fall24_be.apis.notifications;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
@Tag(name = "Notification", description = "Send notification to account.")
public class NotificationsController {
}
