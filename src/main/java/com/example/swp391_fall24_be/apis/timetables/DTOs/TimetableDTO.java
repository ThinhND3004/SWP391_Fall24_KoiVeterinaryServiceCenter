package com.example.swp391_fall24_be.apis.timetables.DTOs;

import com.example.swp391_fall24_be.apis.timetables.TimetableStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TimetableDTO {
    private String id;
    private String veterinarianId;
    private DayOfWeek dayOfWeek;
    private TimetableStatusEnum timetableStatus;
    private String description;         // Ghi chú về lịch trình
    private int maxAppointments;        // Giới hạn số lượng buổi hẹn
    private String location;            // Địa điểm làm việc
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}