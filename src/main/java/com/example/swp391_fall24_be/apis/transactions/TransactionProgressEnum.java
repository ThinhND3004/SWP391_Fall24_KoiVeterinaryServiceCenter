package com.example.swp391_fall24_be.apis.transactions;

public enum TransactionProgressEnum {
    INITIATION_PENDING("Initiation - Pending"),
    AUTHORIZATION_APPROVED("Authorization - Approved"),
    AUTHORIZATION_FAILED("Authorization - Failed"),
    PROCESSING_IN_PROGRESS("Processing - In Progress"),
    PROCESSING_FAILED("Processing - Failed"),
    SETTLEMENT_PENDING("Settlement - Pending"),
    SETTLEMENT_COMPLETED("Settlement - Completed"),
    SETTLEMENT_FAILED("Settlement - Failed"),
    COMPLETION_SUCCESS("Completion - Success"),
    COMPLETION_REVERSED("Completion - Reversed"),
    REVERSAL_PENDING("Reversal - Pending"),
    REVERSAL_COMPLETED("Reversal - Completed");

    private final String progress;

    TransactionProgressEnum(String progress) {
        this.progress = progress;
    }

    public String getProgress() {
        return progress;
    }

    @Override
    public String toString() {
        return this.progress;
    }

}
