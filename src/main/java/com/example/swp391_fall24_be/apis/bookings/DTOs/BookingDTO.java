package com.example.swp391_fall24_be.apis.bookings.DTOs;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.bookings.StatusEnum;
import com.example.swp391_fall24_be.apis.feedbacks.Feedback;
import com.example.swp391_fall24_be.apis.reports.Report;
import com.example.swp391_fall24_be.apis.services.Service;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookingDTO {
    private UUID id;
    private AccountEntity customerId;
    private AccountEntity veterinarianId;
    private Service serviceId;
    private Report reportId;
    private Feedback feedbackId;
    private String description;
    private float totalPrice;
    private String destination;
    private StatusEnum statusEnum;
    private LocalDateTime createdAt;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}
