package com.example.swp391_fall24_be.apis.bookings;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.bookings.DTOs.BookingDTO;
import com.example.swp391_fall24_be.apis.feedbacks.Feedback;
import com.example.swp391_fall24_be.apis.reports.ReportEntity;
import com.example.swp391_fall24_be.apis.services.ServiceEntity;
import com.example.swp391_fall24_be.core.IObject;
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
public class BookingEntity implements IObject<BookingDTO> {
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

    @ManyToOne()
    @JoinColumn(name = "veterian_id")
    private AccountEntity veterian;

    @OneToOne
    @JoinColumn(name = "report_id")
    private ReportEntity report;

    @ManyToOne
    @JoinColumn(name = "feedback_id")
    private Feedback feedbackId;

    @Column(name = "addition_information", nullable = true, columnDefinition = "NVARCHAR(225)")
    private String additionalInformation;

    @Column(name = "service_price", nullable = false)
    private Float servicePrice;

    @Column(name = "travel_price", nullable = true)
    private Float travelPrice;

    @Column(name = "distance_meters")
    private Float distanceMeters;

    @Column(name = "user_address", columnDefinition = "NVARCHAR(225)")
    private String userAddress;

    @Column(name = "meeting_method", nullable = false)
    @Enumerated(EnumType.STRING)
    private MeetingMethodEnum meetingMethodEnum;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum statusEnum;

    @Column(name = "is_decline", nullable = true, columnDefinition = "BIT")
    private Boolean isDecline = false;

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
        bookingDTO.setCustomerFullName(customer.getId());
        bookingDTO.setCustomerFullName(customer.getFirstName() + " " + customer.getLastName());
        if(veterian != null)
            bookingDTO.setVeterinarianFullName(veterian.getFirstName() + " " + veterian.getLastName());
        else
            bookingDTO.setVeterinarianFullName("will be available soon");

        bookingDTO.setServiceId(service.getId());
        bookingDTO.setServiceName(service.getName());
        bookingDTO.setMeetingMethod(meetingMethodEnum.toString());
        bookingDTO.setType(service.getType().name());
        bookingDTO.setServicePrice(servicePrice);
        bookingDTO.setTravelPrice(travelPrice);
        bookingDTO.setTotalPrice(servicePrice + travelPrice * distanceMeters);
        bookingDTO.setReportId(report);
        bookingDTO.setFeedbackId(feedbackId);
        bookingDTO.setAdditionalInformation(additionalInformation);
        bookingDTO.setDistance_meters(distanceMeters);
        bookingDTO.setUserAddress(userAddress);
        bookingDTO.setStatusEnum(statusEnum);
        bookingDTO.setCreatedAt(createdAt);
        bookingDTO.setStartedAt(startedAt);
        bookingDTO.setEndedAt(endedAt);
        return bookingDTO;
    }
}