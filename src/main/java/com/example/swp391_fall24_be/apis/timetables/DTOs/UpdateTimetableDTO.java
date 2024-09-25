package com.example.swp391_fall24_be.apis.timetables.DTOs;

import com.example.swp391_fall24_be.apis.accounts.Account;
import com.example.swp391_fall24_be.apis.timetables.Timetable;
import com.example.swp391_fall24_be.apis.timetables.TimetableStatusEnum;
import com.example.swp391_fall24_be.core.IDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class UpdateTimetableDTO implements IDto<Timetable> {

    @NotNull(message = "Veterinarian is required!")
    private String veterinarianId;
    @NotNull(message = "Day of week is required!")
    private DayOfWeek dayOfWeek;

    @NotNull(message = "Start time is required!")
    private LocalTime startTime;

    @NotNull(message = "End time is required!")
    @JsonProperty("end_time")
    private LocalTime endTime;

    @NotNull(message = "Timetable is required!")
    private TimetableStatusEnum timetableStatus;

    @Size(max = 255, message = "Description should not exceed 255 characters.")
    private String description;

    @Min(0)
    @Max(15)
    private Integer maxAppointments;  // Không cần giá trị mặc định khi cập nhật

    @NotNull(message = "Location is required!")
    @Size(max = 255, message = "Location should not exceed 255 characters.")
    private String location;

    @Override
    public Timetable toEntity() {
        Timetable timetable = new Timetable();
        timetable.setVeterinarianId(new Account(veterinarianId));
        timetable.setDayOfWeek(dayOfWeek);
        timetable.setStartTime(startTime);
        timetable.setEndTime(endTime);
        timetable.setTimetableStatus(timetableStatus);
        timetable.setDescription(description);
        timetable.setMaxAppointments(maxAppointments != null ? maxAppointments : 10);  // Cập nhật nếu có
        timetable.setLocation(location);
        return timetable;
    }
}

