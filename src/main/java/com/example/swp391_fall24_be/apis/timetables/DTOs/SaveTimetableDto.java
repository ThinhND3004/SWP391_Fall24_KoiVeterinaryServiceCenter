package com.example.swp391_fall24_be.apis.timetables.DTOs;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.timetables.Timetable;
import com.example.swp391_fall24_be.core.IDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class SaveTimetableDto {

    @NotNull(message = "Timetable is required")
    private List<RequestTimetableDto> timetableDTOS;

    public List<Timetable> toList(String veterianId) {
        List<Timetable> timetableList = new ArrayList<>();
        for (RequestTimetableDto timetableDTO : timetableDTOS){
            Timetable timetable = new Timetable();
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

            AccountEntity account = new AccountEntity();
            account.setId(veterianId);
            timetable.setVeterian(account);

            timetableList.add(timetable);
        }
        return timetableList;
    }
}
