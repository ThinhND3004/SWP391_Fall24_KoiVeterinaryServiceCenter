package com.example.swp391_fall24_be.apis.services;

public enum ServiceMeetingMethodEnum {
    ONLINE("online"),
    OFFLINE("offline"),
    OFFLINE_HOME("offline_home"),
    OFFLINE_CENTER("offline_center"),
    ALL("all");

    private final String value;


    ServiceMeetingMethodEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
