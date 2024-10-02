package com.example.swp391_fall24_be.apis.timetables.DTOs;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class RequestTimetableDto {
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
}
