package com.example.swp391_fall24_be.apis.bookings.DTOs;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.accounts.AccountRoleEnum;
import com.example.swp391_fall24_be.apis.bookings.Booking;
import com.example.swp391_fall24_be.apis.bookings.StatusEnum;
import com.example.swp391_fall24_be.core.AbstractPagination;

import java.beans.ConstructorProperties;

public class PaginateBookingDTO extends AbstractPagination<Booking> {
    protected StatusEnum status;

    @ConstructorProperties({"page", "unitPerPage", "status"})
    public PaginateBookingDTO(Integer page, Integer unitPerPage, StatusEnum status) {
        super(page, unitPerPage);
        this.status = status;
    }

    @Override
    public Booking toEntity() {
        Booking booking = new Booking();
        booking.setStatusEnum(status);
        return booking;
    }
}
