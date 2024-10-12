package com.example.swp391_fall24_be.apis.feedbacks;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.bookings.BookingEntity;
import com.example.swp391_fall24_be.apis.feedbacks.DTOs.FeedbackDTO;
import com.example.swp391_fall24_be.core.IObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity(name = "feedbacks")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Feedback implements IObject<FeedbackDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private BookingEntity booking;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private AccountEntity customer;

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
        feedbackDTO.setBooking(booking);
        feedbackDTO.setCustomer(customer);
        feedbackDTO.setStarRating(starRating);
        feedbackDTO.setComment(comment);
        feedbackDTO.setCreatedAt(createdAt);
        feedbackDTO.setUpdatedAt(updatedAt);
        feedbackDTO.setAnonymous(anonymous);
        return feedbackDTO;
    }
}
