package com.example.swp391_fall24_be.apis.bookings.DTOs;

import com.example.swp391_fall24_be.apis.bookings.BookingEntity;
import com.example.swp391_fall24_be.core.AbstractPagination;

public class PaginateBookingDTO extends AbstractPagination<BookingEntity> {
    public PaginateBookingDTO(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public BookingEntity toEntity() {
        return null;
    }
}
