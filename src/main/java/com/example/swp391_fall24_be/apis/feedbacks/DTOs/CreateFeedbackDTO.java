package com.example.swp391_fall24_be.apis.feedbacks.DTOs;

import com.example.swp391_fall24_be.apis.bookings.Booking;
import com.example.swp391_fall24_be.apis.feedbacks.Feedback;
import com.example.swp391_fall24_be.core.IDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateFeedbackDTO implements IDto<Feedback> {

//    @NotNull(message = "CustomerID is required!")
//    @JsonProperty("customer_id")
//    private Account customerId;
//
//    @NotNull(message = "BookingID is required!")
//    @JsonProperty("booking_id")
//    private List<Booking> bookingIdList;

    @Min(1)
    @Max(5)
//    @JsonProperty("rate")
    private Double starRating; // Sử dụng Double để tăng độ chính xác

//    @JsonProperty("comment")
    private String comment;

    // Nếu muốn lưu ngày tạo và cập nhật thì đưa lại các trường này
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt; // Khách hàng có thể cập nhật bình luận và đánh giá

    @NotNull(message = "Anonymous Status is required!")
//    @JsonProperty("anonymous")
    private boolean anonymous;

    @Override
    public Feedback toEntity() {
        Feedback feedback = new Feedback();
//        feedback.setCustomerId(customerId);
//        feedback.setBookingIdList(bookingIdList);
        feedback.setStarRating(starRating);
        feedback.setComment(comment);
        feedback.setAnonymous(anonymous);
        feedback.setCreatedAt(createdAt);
        feedback.setUpdatedAt(updatedAt);
        return feedback;
    }
}
