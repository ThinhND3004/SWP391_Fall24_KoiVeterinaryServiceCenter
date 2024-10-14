package com.example.swp391_fall24_be.sub_class;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

@Data
public class TimeSlot {
    private LocalDate date;
    private Map<LocalTime, LocalTime> slots;
}
