package com.example.swp391_fall24_be.apis.reports.dto;

import lombok.Data;

@Data
public class ReportPrescriptionsDto {
    private String medicineId;
    private Integer amount;
}
