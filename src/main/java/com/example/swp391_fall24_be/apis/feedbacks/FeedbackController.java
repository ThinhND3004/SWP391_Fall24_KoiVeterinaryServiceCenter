package com.example.swp391_fall24_be.apis.feedbacks;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.accounts.dtos.AccountDto;
import com.example.swp391_fall24_be.apis.bookings.DTOs.BookingDTO;
import com.example.swp391_fall24_be.apis.bookings.DTOs.CreateBookingDTO;
import com.example.swp391_fall24_be.apis.bookings.DTOs.UpdateBookingDTO;
import com.example.swp391_fall24_be.apis.feedbacks.DTOs.CreateFeedbackDTO;
import com.example.swp391_fall24_be.apis.feedbacks.DTOs.FeedbackDTO;
import com.example.swp391_fall24_be.apis.feedbacks.DTOs.PaginateFeedbackDTO;
import com.example.swp391_fall24_be.apis.feedbacks.DTOs.UpdateFeedbackDTO;
import com.example.swp391_fall24_be.core.AbstractController;
import com.example.swp391_fall24_be.core.ResponseDto;
import com.example.swp391_fall24_be.utils.AuthUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/feedbacks")
@Tag(name = "Feedbacks", description = "Feedback APIs")
public class FeedbackController extends AbstractController
        <Feedback, String, CreateFeedbackDTO, UpdateFeedbackDTO, PaginateFeedbackDTO, FeedbackDTO> {
    @Autowired
    private FeedbackService feedbackService;

    @Override
    public ResponseDto<List<FeedbackDTO>> doGetMany(PaginateFeedbackDTO paginateKoiSpeciesDto) {
        return super.doGetMany(paginateKoiSpeciesDto);
    }

    @Override
    public ResponseDto<FeedbackDTO> doGet(String id) {
        return super.doGet(id);
    }

    @Override
    public ResponseDto<FeedbackDTO> doPost(CreateFeedbackDTO dto) {
        return super.doPost(dto);
    }

    @Override
    public ResponseDto<FeedbackDTO> doPut(String id, UpdateFeedbackDTO dto) {
        return super.doPut(id, dto);
    }

    @Override
    public ResponseDto<FeedbackDTO> doDelete(String id) {
        return super.doDelete(id);
    }

    @GetMapping("/is-feedback/{bookingId}")
    public ResponseDto<Boolean> checkIsFeedback(
            @PathVariable("bookingId") String bookingId
    ){
        try {
            AccountEntity account = AuthUtils.getCurrentAccount();
            return new ResponseDto<>(
                    HttpStatus.OK.value(),
                    "Check is feedback from token success!",
                    feedbackService.isFeedback(account.getId(), bookingId),
                    null
            );
        }
        catch (Exception e){
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseDto<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "Check is feedback from token failed!",
                    null,
                    errors
            );
        }
    }
}
