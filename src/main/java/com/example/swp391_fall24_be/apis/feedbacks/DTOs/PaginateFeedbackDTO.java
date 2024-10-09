package com.example.swp391_fall24_be.apis.feedbacks.DTOs;

import com.example.swp391_fall24_be.apis.feedbacks.FeedbackEntity;
import com.example.swp391_fall24_be.core.AbstractPagination;

public class PaginateFeedbackDTO extends AbstractPagination<FeedbackEntity> {
    public PaginateFeedbackDTO(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public FeedbackEntity toEntity() {
        return null;
    }
}
