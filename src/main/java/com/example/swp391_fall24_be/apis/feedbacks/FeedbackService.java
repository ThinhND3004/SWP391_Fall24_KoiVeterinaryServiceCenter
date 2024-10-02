package com.example.swp391_fall24_be.apis.feedbacks;

import com.example.swp391_fall24_be.apis.feedbacks.DTOs.CreateFeedbackDTO;
import com.example.swp391_fall24_be.apis.feedbacks.DTOs.PaginateFeedbackDTO;
import com.example.swp391_fall24_be.apis.feedbacks.DTOs.UpdateFeedbackDTO;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FeedbackService extends AbstractService<Feedback, String, CreateFeedbackDTO, UpdateFeedbackDTO, PaginateFeedbackDTO> {
    @Override
    protected void beforeCreate(Feedback entity) throws ProjectException {

    }

    @Override
    protected void beforeUpdate(Feedback oldEntity, Feedback newEntity) throws ProjectException {

    }

    @Override
    public Feedback delete(String id) throws ProjectException {
        return null;
    }
}
