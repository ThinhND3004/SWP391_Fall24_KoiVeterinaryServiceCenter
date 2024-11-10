package com.example.swp391_fall24_be.apis.reports.dtos;

import lombok.Data;

@Data
public class ReportPrescriptionsDto {
    private String medicineId;
    private Float medicinePrice;
    private Integer amount;
}
