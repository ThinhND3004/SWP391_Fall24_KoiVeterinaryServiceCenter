package com.example.swp391_fall24_be.core;

import java.util.List;

public class ProjectException extends Exception {
    private final List<ErrorReport> errorReportList;

    public ProjectException(List<ErrorReport> errorReportList) {
        super(errorReportList.get(0).getMessage());
        this.errorReportList = errorReportList;
    }

    public ProjectException(ErrorReport report) {
        super(report.getMessage());
        this.errorReportList = List.of(report);
    }
}
