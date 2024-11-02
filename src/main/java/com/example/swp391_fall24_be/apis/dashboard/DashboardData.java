package com.example.swp391_fall24_be.apis.dashboard;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class DashboardData {
    private final Map<String, Object> data = new HashMap<>();
}
