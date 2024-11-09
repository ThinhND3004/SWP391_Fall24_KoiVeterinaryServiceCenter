package com.example.swp391_fall24_be.apis.bookings.DTOs;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.bookings.BookingEntity;
import com.example.swp391_fall24_be.apis.bookings.StatusEnum;
import com.example.swp391_fall24_be.core.AbstractPagination;

import java.beans.ConstructorProperties;

public class PaginateBookingDTO extends AbstractPagination<BookingEntity> {
    protected StatusEnum status;

    @ConstructorProperties({"page", "unitPerPage", "status"})
    public PaginateBookingDTO(Integer page, Integer unitPerPage, StatusEnum status) {
        super(page, unitPerPage);
        this.status = status;
    }

    @Override
    public BookingEntity toEntity() {
        BookingEntity booking = new BookingEntity();
        booking.setStatusEnum(status);

        return booking;
    }
}
