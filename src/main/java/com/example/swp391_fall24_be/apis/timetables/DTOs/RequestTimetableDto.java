package com.example.swp391_fall24_be.apis.timetables.DTOs;

import lombok.Data;

import java.time.DayOfWeek;

@Data
class TimetableTime {
    public int hours;
    public int minutes;
}
@Data
public class RequestTimetableDto {
    private DayOfWeek dayOfWeek;
    private TimetableTime startTime;
    private TimetableTime endTime;
}
