package com.example.swp391_fall24_be.apis.dashboard;

import com.example.swp391_fall24_be.core.ResponseDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@Tag(name = "dashboard", description = "Dashboard APIs")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/booking")
    @ApiResponse(description = "Get booking dashboard data")
    public ResponseDto<DashboardData> getBookingDashboardData() {
        try {
            DashboardData responseData = dashboardService.getBookingDashboardData();
            return new ResponseDto<>(
                    HttpStatus.OK.value(),
                    "Get booking dashboard data successfully!",
                    responseData,
                    null
            );
        } catch (Exception e) {
            return new ResponseDto<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "Cannot get booking dashboard data!",
                    null,
                    null
            );
        }
    }

    @GetMapping("/veterinarian")
    @ApiResponse(description = "Get veterinarian dashboard data")
    public ResponseDto<DashboardData> getVeterinarianDashboardData() {
        try {
            DashboardData responseData = dashboardService.getVeterinarianDashboardData();
            return new ResponseDto<>(
                    HttpStatus.OK.value(),
                    "Get veterinarian dashboard data successfully!",
                    responseData,
                    null
            );
        } catch (Exception e) {
            return new ResponseDto<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "Cannot get veterinarian dashboard data!",
                    null,
                    null
            );
        }
    }
}
