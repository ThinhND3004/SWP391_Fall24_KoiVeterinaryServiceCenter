package com.example.swp391_fall24_be.core;

import java.util.List;

public class ProjectException extends Exception {
    private final List<ErrorReport> errorReportList;

    public ProjectException(List<ErrorReport> errorReportList) {
        super();
        this.errorReportList = errorReportList;
    }

    public ProjectException(ErrorReport report) {
        super();
        this.errorReportList = List.of(report);
    }
}
