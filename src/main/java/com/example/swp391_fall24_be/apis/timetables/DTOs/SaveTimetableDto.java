package com.example.swp391_fall24_be.apis.timetables.DTOs;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.profiles.ProfileEntity;
import com.example.swp391_fall24_be.apis.timetables.TimetableEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.security.core.parameters.P;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class SaveTimetableDto {

    @NotNull(message = "Timetable is required")
    private List<RequestTimetableDto> timetableDTOS;

    public List<TimetableEntity> toList(ProfileEntity profile) {
        List<TimetableEntity> timetableList = new ArrayList<>();
        for (RequestTimetableDto timetableDTO : timetableDTOS){
            TimetableEntity timetable = new TimetableEntity();
            timetable.setProfile(profile);
            timetable.setDayOfWeek(timetableDTO.getDayOfWeek());
            timetable.setStartTime(LocalTime.of(
                    timetableDTO.getStartTime().getHours(),
                    timetableDTO.getStartTime().minutes)
            );
            timetable.setEndTime(LocalTime.of(
                    timetableDTO.getEndTime().getHours(),
                    timetableDTO.getEndTime().getMinutes()
                    )
            );

            timetableList.add(timetable);
        }
        return timetableList;
    }
}
