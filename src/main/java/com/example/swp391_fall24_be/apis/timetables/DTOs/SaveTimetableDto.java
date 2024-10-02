package com.example.swp391_fall24_be.apis.timetables.DTOs;

import com.example.swp391_fall24_be.apis.timetables.Timetable;
import com.example.swp391_fall24_be.core.IDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Data
public class SaveTimetableDto implements IDto<List<Timetable>> {

    @NotNull(message = "Timetable is required")
    private List<RequestTimetableDto> timetableDTOS;

    @Override
    public List<Timetable> toEntity() {
        List<Timetable> timetableList = new ArrayList<>();
        for (RequestTimetableDto timetableDTO : timetableDTOS){
            Timetable timetable = new Timetable();
            timetable.setStartTime(timetableDTO.getStartTime());
            timetable.setEndTime(timetableDTO.getEndTime());
            timetableList.add(timetable);
        }
        return timetableList;
    }
}
