package com.example.swp391_fall24_be.apis.feedbacks;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FeedbackRepository extends JpaRepository<Feedback, String> {
}
