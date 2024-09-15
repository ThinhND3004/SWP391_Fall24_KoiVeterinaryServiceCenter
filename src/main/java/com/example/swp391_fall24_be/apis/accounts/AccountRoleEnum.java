package com.example.swp391_fall24_be.apis.accounts;

import lombok.Data;

public enum AccountRoleEnum {
    CUSTOMER("customer"),
    STAFF("staff"),
    DELIVERY_STAFF("delivery_staff"),
    ADMIN("admin"),
    MANAGER("manager");

    private final String role;

    AccountRoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}