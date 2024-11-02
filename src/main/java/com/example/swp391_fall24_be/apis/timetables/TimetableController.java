package com.example.swp391_fall24_be.apis.timetables;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.timetables.DTOs.SaveTimetableDto;
import com.example.swp391_fall24_be.apis.timetables.DTOs.TimetableDTO;
import com.example.swp391_fall24_be.core.ResponseDto;
import com.example.swp391_fall24_be.utils.AuthUtils;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.checkerframework.checker.units.qual.A;
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
    private TimetableService timetableService;

    @GetMapping
    private ResponseDto<List<TimetableEntity>> findAll(){
        try {
            return new ResponseDto<>(
                    HttpStatus.OK.value(),
                    "Save timetable successful!",
                    timetableService.doFindByVeterianId(AuthUtils.getCurrentAccount()),
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

    @PostMapping("/save")
    private ResponseDto<List<TimetableDTO>> saveTimetable(
            @RequestBody SaveTimetableDto dto
    ) {
        try {
            AccountEntity currentAccount = AuthUtils.getCurrentAccount();
            return new ResponseDto<>(
                    HttpStatus.OK.value(),
                    "Save timetable successful!",
                    timetableService.doSave(currentAccount, dto.toList(currentAccount.getProfile())),
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
