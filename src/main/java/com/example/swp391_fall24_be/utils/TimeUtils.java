package com.example.swp391_fall24_be.utils;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeUtils {
    public static LocalTime setLocalEndTime(LocalTime startTime, LocalTime estimatedTime){
        return startTime.plusHours(estimatedTime.getHour())
                .plusMinutes(estimatedTime.getMinute());
    }

    public static LocalDateTime setLocalDateEndTime(LocalDateTime startTime, LocalTime estimatedTime){
        return startTime.plusHours(estimatedTime.getHour())
                .plusMinutes(estimatedTime.getMinute());
    }
}
