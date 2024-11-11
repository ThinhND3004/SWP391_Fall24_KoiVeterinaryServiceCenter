package com.example.swp391_fall24_be.apis.feedbacks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, String> {
    @Query("SELECT f FROM feedbacks f WHERE f.customer.id = :accountId AND f.booking.id = :bookingId")
    Optional<Feedback> findFeedbackByAccountIdAndBookingId(@Param("accountId") String accountId, @Param("bookingId") String bookingId);

}
