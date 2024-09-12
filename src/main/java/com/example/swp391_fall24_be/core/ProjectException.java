package com.example.swp391_fall24_be.core;

public class ProjectException extends Exception {
    private final ErrorReport errorReport;

    public ProjectException(ErrorReport errorReport) {
        super();
        this.errorReport = errorReport;
    }
}
