package com.example.swp391_fall24_be.apis.accounts;

public enum AccountRoleEnum {
    CUSTOMER("customer"),
    VETERIAN("veterian"),
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