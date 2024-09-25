package com.example.swp391_fall24_be.apis.timetables.DTOs;

import com.example.swp391_fall24_be.apis.timetables.Timetable;
import com.example.swp391_fall24_be.core.AbstractPagination;

public class PaginateTimetableDTO extends AbstractPagination<Timetable> {
    public PaginateTimetableDTO(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public Timetable toEntity() {
        return null;
    }
}
