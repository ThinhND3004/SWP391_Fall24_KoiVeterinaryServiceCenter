package com.example.swp391_fall24_be.apis.reports;

import com.example.swp391_fall24_be.apis.accounts.dtos.AccountDto;
import com.example.swp391_fall24_be.apis.reports.dtos.CreateReportDto;
import com.example.swp391_fall24_be.apis.reports.dtos.PaginateReportDto;
import com.example.swp391_fall24_be.apis.reports.dtos.ReportDto;
import com.example.swp391_fall24_be.apis.reports.dtos.UpdateReportDto;
import com.example.swp391_fall24_be.core.AbstractController;
import com.example.swp391_fall24_be.core.ResponseDto;
import com.example.swp391_fall24_be.utils.AuthUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reports")
@Tag(name = "Reports", description = "Report APIs")
public class ReportsController extends AbstractController<ReportEntity, String, CreateReportDto, UpdateReportDto, PaginateReportDto, ReportDto> {
    private final ReportsService reportsService;

    public ReportsController(ReportsService reportsService) {
        this.reportsService = reportsService;
    }

    @GetMapping("/by-booking-id/{bookingId}")
    public ResponseDto<ReportDto> getByBookingId(
            @PathVariable("bookingId") String bookingId
    ){
        try {
            return new ResponseDto<>(
                    HttpStatus.OK.value(),
                    "Get report with bookingId " + bookingId + " SUCCESSFUL!",
                    reportsService.getByBookingId(bookingId),
                    null
            );
        }
        catch (Exception e){
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseDto<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "Get report with bookingId " + bookingId + " FAIL!",
                    null,
                    errors
            );
        }

    }
}
