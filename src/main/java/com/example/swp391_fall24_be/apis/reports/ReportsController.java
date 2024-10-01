package com.example.swp391_fall24_be.apis.reports;

import com.example.swp391_fall24_be.apis.reports.dto.CreateReportDto;
import com.example.swp391_fall24_be.apis.reports.dto.PaginateReportDto;
import com.example.swp391_fall24_be.apis.reports.dto.ReportDto;
import com.example.swp391_fall24_be.apis.reports.dto.UpdateReportDto;
import com.example.swp391_fall24_be.core.AbstractController;
import com.example.swp391_fall24_be.core.ResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reports")
@Tag(name = "Reports", description = "Report APIs")
public class ReportsController extends AbstractController<ReportEntity, String, CreateReportDto, UpdateReportDto, PaginateReportDto, ReportDto> {
    private final ReportsService treatmentsService;

    public ReportsController(ReportsService treatmentsService) {
        this.treatmentsService = treatmentsService;
    }

    @Override
    public ResponseDto<List<ReportDto>> doGetMany(PaginateReportDto paginateTreatmentDto) {
        return super.doGetMany(paginateTreatmentDto);
    }

    @Override
    public ResponseDto<ReportDto> doGet(String id) {
        return super.doGet(id);
    }

    @Override
    public ResponseDto<ReportDto> doPost(CreateReportDto dto) {
        return super.doPost(dto);
    }

    @Override
    public ResponseDto<ReportDto> doPut(String id, UpdateReportDto dto) {
        return super.doPut(id, dto);
    }

    @Override
    public ResponseDto<ReportDto> doDelete(String id) {
        return super.doDelete(id);
    }
}
