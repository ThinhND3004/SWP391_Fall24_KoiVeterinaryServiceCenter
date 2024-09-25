package com.example.swp391_fall24_be.apis.timetables;

import com.example.swp391_fall24_be.apis.timetables.DTOs.CreateTimetableDTO;
import com.example.swp391_fall24_be.apis.timetables.DTOs.PaginateTimetableDTO;
import com.example.swp391_fall24_be.apis.timetables.DTOs.UpdateTimetableDTO;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;

import java.util.UUID;

public class TimetableService extends AbstractService<Timetable, UUID, CreateTimetableDTO, UpdateTimetableDTO, PaginateTimetableDTO> {
    @Override
    protected void beforeCreate(Timetable entity) throws ProjectException {

    }

    @Override
    protected void beforeUpdate(Timetable oldEntity, Timetable newEntity) throws ProjectException {

    }

    @Override
    public Timetable delete(UUID id) throws ProjectException {
        return null;
    }
}
