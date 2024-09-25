package com.example.swp391_fall24_be.apis.services.DTOs;

import com.example.swp391_fall24_be.apis.services.MeetingMethodEnum;
import com.example.swp391_fall24_be.apis.services.Service;
import com.example.swp391_fall24_be.apis.services.ServiceTypeEnum;
import com.example.swp391_fall24_be.core.IDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ServiceDTO {
    private UUID id;
    private String name;
    private ServiceTypeEnum serviceType;
    private String description;
    private MeetingMethodEnum meetingMethod;
    private float price;
    private float travelPricePerMeters;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean disable;
}
