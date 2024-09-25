package com.example.swp391_fall24_be.apis.timetables.DTOs;

import com.example.swp391_fall24_be.apis.accounts.Account;
import com.example.swp391_fall24_be.apis.timetables.Timetable;
import com.example.swp391_fall24_be.apis.timetables.TimetableStatusEnum;
import com.example.swp391_fall24_be.core.IDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

//@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateTimetableDTO implements IDto<Timetable> {

    @NotNull(message = "Veterinarian is required!")
    private String veterinarianId;

    @NotNull(message = "Day of week is required!")
    private DayOfWeek dayOfWeek;

    @NotNull(message = "Start time is required!")
    private LocalTime startTime;

    @NotNull(message = "End time is required!")
    private LocalTime endTime;

    @NotNull(message = "Timetable is required!")
    private TimetableStatusEnum timetableStatus;

    @Size(max = 255, message = "Description should not exceed 255 characters.")
    private String description;         // Ghi chú về lịch trình (có thể null)

    @Min(0)
    @Max(10)
    private int maxAppointments = 10;        // Giới hạn số lượng buổi hẹn, mặc định là 10

    @NotNull(message = "Location is required!")
    @Size(max = 255, message = "Location should not exceed 255 characters.")
    private String location;            // Địa điểm làm việc

    @Override
    public Timetable toEntity() {
        Timetable timetable = new Timetable();
        timetable.setVeterinarianId(new Account(veterinarianId));
        timetable.setDayOfWeek(dayOfWeek);
        timetable.setStartTime(startTime);
        timetable.setEndTime(endTime);
//        timetable.setActive(active);
        timetable.setDescription(description);
        timetable.setMaxAppointments(maxAppointments);
        timetable.setLocation(location);
        return timetable;
    }
}

