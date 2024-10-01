package com.example.swp391_fall24_be.apis.reports;

import com.example.swp391_fall24_be.apis.reports.dto.CreateReportDto;
import com.example.swp391_fall24_be.apis.reports.dto.PaginateReportDto;
import com.example.swp391_fall24_be.apis.reports.dto.UpdateReportDto;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReportsService extends AbstractService<ReportEntity, String, CreateReportDto, UpdateReportDto, PaginateReportDto> {
    @Autowired
    private final ReportsRepository reportsRepository;

    public ReportsService(ReportsRepository treatmentsRepository) {
        this.reportsRepository = treatmentsRepository;
    }

    @Override
    protected void beforeCreate(ReportEntity entity) throws ProjectException {

    }

    @Override
    protected void beforeUpdate(ReportEntity oldEntity, ReportEntity newEntity) throws ProjectException {

    }

    @Override
    public ReportEntity delete(String id) throws ProjectException {
        return null;
    }
}
