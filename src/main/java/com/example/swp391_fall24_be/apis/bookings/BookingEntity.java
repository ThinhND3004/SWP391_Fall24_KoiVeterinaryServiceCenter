package com.example.swp391_fall24_be.apis.bookings;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.bookings.DTOs.BookingDTO;
import com.example.swp391_fall24_be.apis.feedbacks.FeedbackEntity;
import com.example.swp391_fall24_be.apis.reports.ReportEntity;
import com.example.swp391_fall24_be.apis.services.ServiceEntity;
import com.example.swp391_fall24_be.apis.transactions.TransactionEntity;
import com.example.swp391_fall24_be.core.IObject;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

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

    @Column(name = "addition_information", nullable = true, columnDefinition = "TEXT")
    @Size(max = 500)
    private String additionalInformation;

    @Column(name = "total_price", nullable = false)
    @Min(0)
    private Float totalPrice;

    @Column(name = "distance_kilometers", nullable = false)
    private Float distanceKilometers;

    @Column(name = "user_address", nullable = false, columnDefinition = "TEXT")
    @Size(max = 100)
    private String userAddress;

    @Column(name = "meeting_method", nullable = false)
    @Enumerated(EnumType.STRING)
    private MeetingMethodEnum meetingMethodEnum;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum statusEnum;

    @Column(name = "is_decline", nullable = false, columnDefinition = "BIT")
    private Boolean isDecline;

    @Column(name = "created_at", nullable = true, columnDefinition = "DATETIME")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "started_at",nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime startedAt;

    @Column(name = "ended_at", columnDefinition = "DATETIME")
    private LocalDateTime endedAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private AccountEntity customer;

    @ManyToOne
    @JoinColumn(name = "veterinarian_id", nullable = false)
    private AccountEntity veterinarianIsBooked;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    private TransactionEntity bookingTransaction;

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceEntity service;

    @OneToOne
    @JoinColumn(name = "report_id")
    private ReportEntity report;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<FeedbackEntity> feedbackList;

    @Override
    public BookingDTO toResponseDto() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(id);
        bookingDTO.setCustomerFullName(customer.getFirstName() + " " + customer.getLastName());
        bookingDTO.setVeterinarianFullName(veterinarianIsBooked.getFirstName() + " " + veterinarianIsBooked.getLastName());
        bookingDTO.setServiceName(service.getName());
        bookingDTO.setReportId(report);
//        bookingDTO.setFeedback(feedback);
        bookingDTO.setAdditionalInformation(additionalInformation);
        bookingDTO.setUserAddress(userAddress);
        bookingDTO.setStatusEnum(statusEnum);
        bookingDTO.setTotalPrice(totalPrice);
        bookingDTO.setCreatedAt(createdAt);
        bookingDTO.setStartedAt(startedAt);
        bookingDTO.setEndedAt(endedAt);
        return bookingDTO;
    }
}
