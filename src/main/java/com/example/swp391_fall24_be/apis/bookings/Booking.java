package com.example.swp391_fall24_be.apis.bookings;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.bookings.DTOs.BookingDTO;
import com.example.swp391_fall24_be.apis.feedbacks.Feedback;
import com.example.swp391_fall24_be.apis.reports.ReportEntity;
import com.example.swp391_fall24_be.apis.services.ServiceEntity;
import com.example.swp391_fall24_be.core.IObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity(name = "bookings")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Booking implements IObject<BookingDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private AccountEntity customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceEntity service;

    @ManyToOne(optional = false)
    @JoinColumn(name = "veterian_id", nullable = false)
    private AccountEntity veterian;

    @OneToOne
    @JoinColumn(name = "report_id")
    private ReportEntity report;

    @ManyToOne
    @JoinColumn(name = "feedback_id")
    private Feedback feedbackId;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    @Size(max = 500)
    private String description;

    @Column(name = "service_price", nullable = false)
    @Min(0)
    private float servicePrice;

    @Column(name = "travel_price", nullable = false)
    @Min(0)
    private float travelPrice;

    @Column(name = "destination", nullable = false, columnDefinition = "TEXT")
    @Size(max = 100)
    private String destination;

    @Column(name = "meeting_method", nullable = false)
    @Enumerated(EnumType.STRING)
    private MeetingMethodEnum meetingMethodEnum;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum statusEnum;

    @Column(name = "is_decline", nullable = false, columnDefinition = "BIT")
    private boolean isDecline;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "started_at",nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime startedAt;

    @Column(name = "ended_at", columnDefinition = "DATETIME")
    private LocalDateTime endedAt;

    @Override
    public BookingDTO toResponseDto() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(id);
        bookingDTO.setCustomer(customer);
        bookingDTO.setVeterian(veterian);
        bookingDTO.setService(service);
        bookingDTO.setReportId(report);
        bookingDTO.setFeedbackId(feedbackId);
        bookingDTO.setDescription(description);
        bookingDTO.setDestination(destination);
        bookingDTO.setStatusEnum(statusEnum);
        bookingDTO.setCreatedAt(createdAt);
        bookingDTO.setStartedAt(startedAt);
        bookingDTO.setEndedAt(endedAt);
        return bookingDTO;
    }
}
