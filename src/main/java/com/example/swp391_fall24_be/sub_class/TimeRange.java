package com.example.swp391_fall24_be.sub_class;

import lombok.Data;

import java.time.LocalTime;

@Data
public class TimeRange {
    private LocalTime startTime;
    private LocalTime endTime;

    public TimeRange(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
