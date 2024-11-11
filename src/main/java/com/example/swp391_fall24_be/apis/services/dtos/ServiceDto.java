package com.example.swp391_fall24_be.apis.services.dtos;

import com.example.swp391_fall24_be.apis.services.ServiceMeetingMethodEnum;
import com.example.swp391_fall24_be.apis.services.ServiceTypeEnum;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class ServiceDto {
    private String id;
    private String name;
    private ServiceTypeEnum type;
    private String overview;
    private String description;
    private ServiceMeetingMethodEnum meetingMethod;
    private Float price;
    private Float pricePerKoi;
    private Float smallPondPrice;
    private Float mediumPondPrice;
    private Float largePondPrice;
    private Float travelPricePerMeter;
    private LocalTime estimatedTime;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDisable;
    private String serImageId;
}