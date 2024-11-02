package com.example.swp391_fall24_be.apis.bookings.DTOs;

import com.example.swp391_fall24_be.apis.bookings.StatusEnum;
import com.example.swp391_fall24_be.apis.feedbacks.Feedback;
import com.example.swp391_fall24_be.apis.reports.ReportEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookingDTO {
    private String id;
    private String customerFullName;
    private String veterinarianFullName;
    private String serviceId;
    private String serviceName;
    private String meetingMethod;
    private String type;
    private ReportEntity reportId;
    private Feedback feedbackId;
    private String additionalInformation;
    private float servicePrice;
    private float travelPrice;
    private float distance_meters;
    private float totalPrice;
    private String userAddress;
    private StatusEnum statusEnum;
    private LocalDateTime createdAt;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}
