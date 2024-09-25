package com.example.swp391_fall24_be.apis.bookings;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.bookings.DTOs.BookingDTO;
import com.example.swp391_fall24_be.apis.feedbacks.Feedback;
import com.example.swp391_fall24_be.apis.reports.Report;
import com.example.swp391_fall24_be.apis.services.MeetingMethodEnum;
import com.example.swp391_fall24_be.apis.services.Service;
import com.example.swp391_fall24_be.core.IObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "bookings")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Booking implements IObject<BookingDTO> {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, columnDefinition = "UNIQUEIDENTIFIER")
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private AccountEntity customerId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    private Service serviceId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "veterinarian_id", nullable = false)
    private AccountEntity veterinarianId;

    @OneToOne(optional = true)
    @JoinColumn(name = "report_id")
    private Report reportId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "feedback_id")
    private Feedback feedbackId;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    @Size(max = 500)
    private String description;

    @Column(name = "total_price", nullable = false, columnDefinition = "NVARCHAR(20)")
    @Min(0)
    private float totalPrice;

    @Column(name = "destination", nullable = false, columnDefinition = "CHAR(10)")
    @Size(max = 100)
    private String destination;

    @Column(name = "meeting_method", nullable = false, columnDefinition = "CHAR(10)")
    @Enumerated(EnumType.STRING)
    private MeetingMethodEnum meetingMethodEnum;

    @Column(name = "status", nullable = false, columnDefinition = "NVARCHAR(20)")
    @Enumerated(EnumType.STRING)
    private StatusEnum statusEnum;

    @Column(name = "decline", nullable = false, columnDefinition = "BIT")
    private boolean decline;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(name = "started_at",nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime startedAt;

    @Column(name = "ended_at", nullable = false, columnDefinition = "DATETIME")
    @LastModifiedDate
    private LocalDateTime endedAt;

    @Override
    public BookingDTO toResponseDto() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(id);
        bookingDTO.setCustomerId(customerId);
        bookingDTO.setVeterinarianId(veterinarianId);
        bookingDTO.setServiceId(serviceId);
        bookingDTO.setReportId(reportId);
        bookingDTO.setFeedbackId(feedbackId);
        bookingDTO.setDescription(description);
        bookingDTO.setTotalPrice(totalPrice);
        bookingDTO.setDestination(destination);
        bookingDTO.setStatusEnum(statusEnum);
        bookingDTO.setCreatedAt(createdAt);
        bookingDTO.setStartedAt(startedAt);
        bookingDTO.setEndedAt(endedAt);
        return bookingDTO;
    }
}
