package com.example.swp391_fall24_be.apis.accounts;

import lombok.Data;

public enum AccountRoleEnum {
    CUSTOMER("customer"),
    VETERIAN("Veterian"),
    STAFF("staff"),
    DELIVERY_STAFF("delivery_staff"),
    ADMIN("admin"),
    MANAGER("manager");

    private final String value;

    AccountRoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}