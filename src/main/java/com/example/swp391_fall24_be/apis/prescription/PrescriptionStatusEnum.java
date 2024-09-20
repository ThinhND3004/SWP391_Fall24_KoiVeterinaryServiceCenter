package com.example.swp391_fall24_be.apis.prescription;


public enum PrescriptionStatusEnum {
    PENDING("pending"),
    IN_DELIVERING("in_delivering"),
    COMPLETE("complete");

    private final String status;

    PrescriptionStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
