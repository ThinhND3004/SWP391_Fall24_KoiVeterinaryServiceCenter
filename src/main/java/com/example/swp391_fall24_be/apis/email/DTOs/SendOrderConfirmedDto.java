package com.example.swp391_fall24_be.apis.email.DTOs;

import org.springframework.web.bind.annotation.RequestParam;

public class SendOrderConfirmedDto {
    public String to;
    public String recipientName;
    public String serviceName;
    public String date;
    public String time;
    public String location;
    public String amount;
    public String meetingLink;
}
