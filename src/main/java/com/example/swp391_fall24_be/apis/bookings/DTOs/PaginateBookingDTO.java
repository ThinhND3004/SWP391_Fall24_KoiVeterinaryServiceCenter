package com.example.swp391_fall24_be.apis.bookings.DTOs;

import com.example.swp391_fall24_be.apis.bookings.Booking;
import com.example.swp391_fall24_be.core.AbstractPagination;

public class PaginateBookingDTO extends AbstractPagination<Booking> {
    public PaginateBookingDTO(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public Booking toEntity() {
        return null;
    }
}
