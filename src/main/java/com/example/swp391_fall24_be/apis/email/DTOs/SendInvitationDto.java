package com.example.swp391_fall24_be.apis.email.DTOs;

import com.example.swp391_fall24_be.apis.bookings.MeetingMethodEnum;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

public class SendInvitationDto {
    public String to;
    public String recipientName;
    public String serviceName;
    public MeetingMethodEnum serviceMethod;

    public String date;

    public String time;
    public String location;
    public String meetingLink;
}
