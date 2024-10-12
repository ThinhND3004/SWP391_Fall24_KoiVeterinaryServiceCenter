package com.example.swp391_fall24_be.apis.timetables;

import com.example.swp391_fall24_be.annotations.CurrentAccount;
import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.timetables.DTOs.SaveTimetableDto;
import com.example.swp391_fall24_be.core.ResponseDto;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/timetables")
@Tag(name = "Timetables", description = "Timetables APIs")
public class TimetableController {
    @Autowired
    private TimetableService service;

    @GetMapping("/{veterianId}")
    private ResponseDto<List<TimetableEntity>> findAll(@PathVariable("veterianId") String veterianId){
        try {
            return new ResponseDto<>(
                    HttpStatus.OK.value(),
                    "Save timetable successful!",
                    service.doFindByVeterianId(veterianId),
                    null
            );

        }
        catch(Exception e){
            List<String> errorList = new ArrayList<>();
            errorList.add(e.getMessage());
            return new ResponseDto<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "Save timetable failed!",
                    null,
                    errorList
            );
        }
    }

    @PostMapping("/save/{veterianId}")
    private ResponseDto<List<TimetableEntity>> saveTimetable(
            @Parameter(hidden = true) @CurrentAccount AccountEntity account,
            @RequestBody SaveTimetableDto dto
    ) {
        try {
            return new ResponseDto<>(
                    HttpStatus.OK.value(),
                    "Save timetable successful!",
                    service.doSave(account.getId(), dto.toList()),
                    null
            );

        }
        catch(Exception e){
            List<String> errorList = new ArrayList<>();
            errorList.add(e.getMessage());
            return new ResponseDto<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "Save timetable failed!",
                    null,
                    errorList
            );
        }

    }
}
