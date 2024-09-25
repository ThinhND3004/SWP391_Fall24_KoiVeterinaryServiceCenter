package com.example.swp391_fall24_be.apis.reports;

import com.example.swp391_fall24_be.apis.reports.dto.CreateReportDto;
import com.example.swp391_fall24_be.apis.reports.dto.PaginateReportDto;
import com.example.swp391_fall24_be.apis.reports.dto.UpdateReportDto;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReportsService extends AbstractService<ReportEntity, UUID, CreateReportDto, UpdateReportDto, PaginateReportDto> {
    private final ReportsRepository treatmentsRepository;

    public ReportsService(ReportsRepository treatmentsRepository) {
        this.treatmentsRepository = treatmentsRepository;
    }

    @Override
    protected void beforeCreate(ReportEntity entity) throws ProjectException {

    }

    @Override
    protected void beforeUpdate(ReportEntity oldEntity, ReportEntity newEntity) throws ProjectException {

    }

    @Override
    public ReportEntity delete(UUID id) throws ProjectException {
        return null;
    }
}
