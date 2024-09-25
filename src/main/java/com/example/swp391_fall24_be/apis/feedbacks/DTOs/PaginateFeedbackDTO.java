package com.example.swp391_fall24_be.apis.feedbacks.DTOs;

import com.example.swp391_fall24_be.apis.feedbacks.Feedback;
import com.example.swp391_fall24_be.core.AbstractPagination;

public class PaginateFeedbackDTO extends AbstractPagination<Feedback> {
    public PaginateFeedbackDTO(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public Feedback toEntity() {
        return null;
    }
}
