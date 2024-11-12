package com.example.swp391_fall24_be.apis.feedbacks.DTOs;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.accounts.AccountRoleEnum;
import com.example.swp391_fall24_be.apis.bookings.BookingEntity;
import com.example.swp391_fall24_be.apis.feedbacks.Feedback;
import com.example.swp391_fall24_be.core.AbstractPagination;

import java.beans.ConstructorProperties;

public class PaginateFeedbackDTO extends AbstractPagination<Feedback> {
    public String bookingId;

    @ConstructorProperties({"page", "unitPerPage", "bookingId"})
    public PaginateFeedbackDTO(Integer page, Integer unitPerPage, String bookingId) {
        super(page, unitPerPage);
        this.bookingId = bookingId;
    }

    @Override
    public Feedback toEntity() {
        Feedback feedback = new Feedback();

        BookingEntity booking = new BookingEntity();
        booking.setId(bookingId);
        feedback.setBooking(booking);

        return feedback;
    }
}
