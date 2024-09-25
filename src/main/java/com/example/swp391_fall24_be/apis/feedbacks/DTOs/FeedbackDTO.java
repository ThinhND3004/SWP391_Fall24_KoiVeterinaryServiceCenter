package com.example.swp391_fall24_be.apis.feedbacks.DTOs;

import com.example.swp391_fall24_be.apis.accounts.Account;
import com.example.swp391_fall24_be.apis.bookings.Booking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FeedbackDTO {
    private String id;
    private Account customerId;
    private List<Booking> bookingIdList;
    private Double starRating;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt; //due to Customer can be able to update Comment and Rating
    private boolean anonymous; //customer can be anonymous
}