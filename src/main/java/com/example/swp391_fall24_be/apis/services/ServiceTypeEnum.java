package com.example.swp391_fall24_be.apis.services;

public enum ServiceTypeEnum {
    KOI("koi"),
    POND("pond");
    private final String value;

    ServiceTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
