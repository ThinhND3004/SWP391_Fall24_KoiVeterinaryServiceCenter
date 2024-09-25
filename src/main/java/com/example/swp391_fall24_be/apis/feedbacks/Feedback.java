package com.example.swp391_fall24_be.apis.feedbacks;

import com.example.swp391_fall24_be.apis.accounts.Account;
import com.example.swp391_fall24_be.apis.bookings.Booking;
import com.example.swp391_fall24_be.apis.feedbacks.DTOs.FeedbackDTO;
import com.example.swp391_fall24_be.core.IObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "feedbacks")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Feedback implements IObject<FeedbackDTO> {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "id", nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID().toString();
    }

    @OneToMany
    @JoinColumn(name = "booking_id", nullable = false)
    private List<Booking> bookingIdList;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Account customerId;

    @Column(name = "rate", nullable = false, columnDefinition = "FLOAT")
    private Double starRating;

    @Column(name = "comment")
    private String comment;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME")
    @LastModifiedDate
    private LocalDateTime updatedAt; //due to Customer can be able to update Comment and Rating

    @Column(name = "anonymous", nullable = false, columnDefinition = "BIT")
    private boolean anonymous;

    @Override
    public FeedbackDTO toResponseDto() {
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setId(id);
        feedbackDTO.setBookingIdList(bookingIdList);
        feedbackDTO.setCustomerId(customerId);
        feedbackDTO.setStarRating(starRating);
        feedbackDTO.setComment(comment);
        feedbackDTO.setCreatedAt(createdAt);
        feedbackDTO.setUpdatedAt(updatedAt);
        feedbackDTO.setAnonymous(anonymous);
        return feedbackDTO;
    }
}