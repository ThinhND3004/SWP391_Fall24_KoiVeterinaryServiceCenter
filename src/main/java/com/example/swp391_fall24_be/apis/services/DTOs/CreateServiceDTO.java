package com.example.swp391_fall24_be.apis.services.DTOs;

import com.example.swp391_fall24_be.apis.services.MeetingMethodEnum;
import com.example.swp391_fall24_be.apis.services.Service;
import com.example.swp391_fall24_be.apis.services.ServiceTypeEnum;
import com.example.swp391_fall24_be.core.IDto;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateServiceDTO implements IDto<Service> {
//    private UUID id;
    private String name;
    private ServiceTypeEnum serviceType; // Loại dịch vụ
    private String description;
    private int duration;                // Thời gian dự kiến của dịch vụ
    private boolean available;           // Dịch vụ có khả dụng hay không
    private String location;
    private float price;
    private float travelPricePerMeters;
    private MeetingMethodEnum meetingMethodEnum;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean disable;

    @Override
    public Service toEntity() {
        Service service = new Service();
//        service.setId(id);
        service.setName(name);
//        service.setServiceType(serviceType);
        service.setDescription(description);
//        service.setDuration(duration);
//        service.setAvailable(available);
//        service.setLocation(location);
        service.setPrice(price);
        service.setTravelPricePerMeters(travelPricePerMeters);
        service.setMeetingMethodEnum(meetingMethodEnum);
        service.setCreatedAt(createdAt);
        service.setUpdatedAt(updatedAt);
        service.setDisable(disable);
        return service;
    }
}
