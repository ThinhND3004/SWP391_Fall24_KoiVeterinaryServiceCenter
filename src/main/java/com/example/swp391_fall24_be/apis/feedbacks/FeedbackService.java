package com.example.swp391_fall24_be.apis.feedbacks;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.feedbacks.DTOs.CreateFeedbackDTO;
import com.example.swp391_fall24_be.apis.feedbacks.DTOs.FeedbackDTO;
import com.example.swp391_fall24_be.apis.feedbacks.DTOs.PaginateFeedbackDTO;
import com.example.swp391_fall24_be.apis.feedbacks.DTOs.UpdateFeedbackDTO;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import com.example.swp391_fall24_be.utils.AuthUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeedbackService extends AbstractService<Feedback, String, CreateFeedbackDTO, UpdateFeedbackDTO, PaginateFeedbackDTO> {
    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    protected void beforeCreate(Feedback entity) throws ProjectException {
        AccountEntity account = AuthUtils.getCurrentAccount();
        entity.setCustomer(account);
    }

    @Override
    protected void beforeUpdate(Feedback oldEntity, Feedback newEntity) throws ProjectException {

    }

    @Override
    public Feedback delete(String id) throws ProjectException {
        return null;
    }

    public FeedbackDTO getCurrentBooking(String accountId, String bookingId) throws Exception {
        Optional<Feedback> findFeedbackResult = feedbackRepository.findFeedbackByAccountIdAndBookingId(accountId,bookingId);
        if(findFeedbackResult.isPresent()){
            return findFeedbackResult.get().toResponseDto();
        }
        throw new Exception("Cannot find feedback");

    }

    public Boolean isFeedback(String accountId,String bookingId){
        Optional<Feedback> findFeedbackResult = feedbackRepository.findFeedbackByAccountIdAndBookingId(accountId,bookingId);
        return findFeedbackResult.isPresent();
    }
}
