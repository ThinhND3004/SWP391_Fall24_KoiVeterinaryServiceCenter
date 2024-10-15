package com.example.swp391_fall24_be.sub_class;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class TimeSlot {
    private LocalDate date;
    private List<TimeRange> slots = new ArrayList<>();
}
