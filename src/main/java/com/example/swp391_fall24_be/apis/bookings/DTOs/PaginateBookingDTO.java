package com.example.swp391_fall24_be.apis.bookings.DTOs;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.bookings.BookingEntity;
import com.example.swp391_fall24_be.apis.bookings.StatusEnum;
import com.example.swp391_fall24_be.core.AbstractPagination;

import java.beans.ConstructorProperties;

public class PaginateBookingDTO extends AbstractPagination<BookingEntity> {
    protected StatusEnum status;
    public String veterianEmail;

    @ConstructorProperties({"page", "unitPerPage", "status", "veterianEmail"})
    public PaginateBookingDTO(Integer page, Integer unitPerPage, StatusEnum status, String accountId) {
        super(page, unitPerPage);
        this.status = status;
        this.veterianEmail = accountId;
    }

    @Override
    public BookingEntity toEntity() {
        BookingEntity booking = new BookingEntity();
        booking.setStatusEnum(status);

        if(veterianEmail != null && !veterianEmail.isEmpty()){
            AccountEntity veterian = new AccountEntity();
            veterian.setEmail(veterianEmail);
            booking.setVeterian(veterian);
        }

        return booking;
    }
}
