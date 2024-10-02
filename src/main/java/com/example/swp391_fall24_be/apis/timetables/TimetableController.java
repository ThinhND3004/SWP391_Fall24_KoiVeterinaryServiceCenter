package com.example.swp391_fall24_be.apis.timetables;

import com.example.swp391_fall24_be.apis.timetables.DTOs.SaveTimetableDto;
import com.example.swp391_fall24_be.apis.timetables.DTOs.TimetableDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/timetable")
@Tag(name = "Timetables", description = "Timetables APIs")
public class TimetableController {
    @PostMapping("/save")
    private TimetableDTO saveTimetable(@RequestBody SaveTimetableDto dto) {
        return null;
    }
}
