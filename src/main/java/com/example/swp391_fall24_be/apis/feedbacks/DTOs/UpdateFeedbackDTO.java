package com.example.swp391_fall24_be.apis.feedbacks.DTOs;

import com.example.swp391_fall24_be.apis.feedbacks.Feedback;
import com.example.swp391_fall24_be.core.IDto;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateFeedbackDTO implements IDto<Feedback> {

    @NotNull(message = "Rating is required!")
    @Min(1)
    @Max(5)
    private Double starRating;

    @Size(max = 255, message = "Comment should not exceed 255 characters.")
    @Column(name = "comment")
    private String comment;

    @NotNull(message = "Anonymous status is required!")
    private boolean anonymous;

    @Override
    public Feedback toEntity() {
        Feedback feedback = new Feedback();
        feedback.setStarRating(starRating);
        feedback.setComment(comment != null ? comment : "");
        feedback.setAnonymous(anonymous);
        return feedback;
    }
}

