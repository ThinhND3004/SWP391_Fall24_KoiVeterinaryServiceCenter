package com.example.swp391_fall24_be.apis.bookings.DTOs;

import com.example.swp391_fall24_be.apis.bookings.Booking;
import com.example.swp391_fall24_be.apis.bookings.StatusEnum;
import com.example.swp391_fall24_be.core.IDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class UpdateBookingDTO implements IDto<Booking> {
//    @JsonProperty("customer_id")
//    @NotBlank(message = "CustomerID is required!")
//    private Account customerId;

//    @JsonProperty("veterinarian_id")
//    @NotBlank(message = "VeterinarianID is required!")
//    private Account veterinarianId;

//    @JsonProperty("service_id")
//    @NotBlank(message = "ServiceID is required!")
//    private Service serviceId;

//    @JsonProperty("medical_report_id")
//    @NotBlank(message = "medicalReportID is required!")
//    private Report medicalReportId;
//
//    @JsonProperty("feedback_id")
//    @NotBlank(message = "feedbackID is required!")
//    private Feedback feedbackId;

    @JsonProperty("description")
    @NotBlank(message = "Description is required!")
    @Size(max = 255, message = "Description length must be between 1 and 255 characters")
    private String description;

    @NotBlank(message = "Price is required!")
    @Column(name = "total_price", nullable = false, columnDefinition = "FLOAT")
    @JsonProperty("total_price")
    private float totalPrice;

    @NotBlank(message = "Destination is required!")
    @Size(max = 255, message = "Destination length must be between 1 and 255 characters")
    @JsonProperty("destination")
    private String destination;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @JsonProperty("status")
    private StatusEnum statusEnum;

    @NotBlank(message = "Start Date is required!")
    @JsonProperty("start_at")
    private LocalDateTime startedAt;

    @NotBlank(message = "End Date is required!")
    @JsonProperty("ended_at")
    private LocalDateTime endedAt;
    @Override
    public Booking toEntity() {
        Booking booking = new Booking();
//        booking.setCustomerId(customerId);
//        booking.setVeterinarianId(veterinarianId);
////        booking.setServiceId(serviceId);
//        booking.setReportId(medicalReportId);
//        booking.setFeedbackId(feedbackId);
        booking.setDescription(description);
        booking.setTotalPrice(totalPrice);
        booking.setDestination(destination);
        booking.setStatusEnum(statusEnum);
        booking.setStartedAt(startedAt);
        booking.setEndedAt(endedAt);
        return booking;
    }
}
