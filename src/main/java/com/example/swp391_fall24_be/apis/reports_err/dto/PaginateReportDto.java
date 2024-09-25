package com.example.swp391_fall24_be.apis.reports.dto;

import com.example.swp391_fall24_be.core.AbstractPagination;
import com.example.swp391_fall24_be.apis.reports.ReportEntity;

public class PaginateReportDto extends AbstractPagination<ReportEntity> {

    public PaginateReportDto(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public ReportEntity toEntity() {
        return null;
    }
}
