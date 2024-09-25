package com.example.swp391_fall24_be.apis.services.DTOs;

import com.example.swp391_fall24_be.apis.services.MeetingMethodEnum;
import com.example.swp391_fall24_be.apis.services.Service;
import com.example.swp391_fall24_be.apis.services.ServiceTypeEnum;
import com.example.swp391_fall24_be.core.IDto;

import java.time.LocalDateTime;
import java.util.UUID;

public class UpdateServiceDTO implements IDto<Service> {
    private String name;
    private String description;
    private int duration;                // Thời gian dự kiến của dịch vụ
    private boolean available;           // Dịch vụ có khả dụng hay không
    private String location;
    private float price;
    private float travelPricePerMeters;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean disable;
    @Override
    public Service toEntity() {
        return null;
    }
}
