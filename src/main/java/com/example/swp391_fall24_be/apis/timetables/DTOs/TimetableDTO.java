package com.example.swp391_fall24_be.apis.timetables.DTOs;

import com.example.swp391_fall24_be.sub_class.TimeRange;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TimetableDTO {
    private String id;
    private String dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<TimeRange> bookedSlots;
}
