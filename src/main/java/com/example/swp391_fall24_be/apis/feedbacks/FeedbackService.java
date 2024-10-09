package com.example.swp391_fall24_be.apis.feedbacks;

import com.example.swp391_fall24_be.apis.feedbacks.DTOs.CreateFeedbackDTO;
import com.example.swp391_fall24_be.apis.feedbacks.DTOs.PaginateFeedbackDTO;
import com.example.swp391_fall24_be.apis.feedbacks.DTOs.UpdateFeedbackDTO;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService extends AbstractService<FeedbackEntity, String, CreateFeedbackDTO, UpdateFeedbackDTO, PaginateFeedbackDTO> {
    @Override
    protected void beforeCreate(FeedbackEntity entity) throws ProjectException {

    }

    @Override
    protected void beforeUpdate(FeedbackEntity oldEntity, FeedbackEntity newEntity) throws ProjectException {

    }

    @Override
    public FeedbackEntity delete(String id) throws ProjectException {
        return null;
    }
}
