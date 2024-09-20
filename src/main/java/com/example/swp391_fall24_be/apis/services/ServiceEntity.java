package com.example.swp391_fall24_be.apis.services;

import com.example.swp391_fall24_be.apis.services.dtos.ServiceDto;
import com.example.swp391_fall24_be.core.IObject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class ServiceEntity implements IObject<ServiceDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false, unique = true, columnDefinition = "VARCHAR(20)")
    private String name;

    @Column(name = "type", nullable = false)
    private ServiceTypeEnum type;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "meeting_method", nullable = false)
    private ServiceMeetingMethodEnum meetingMethod;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "travel_price_per_meter", nullable = false)
    private float travelPricePerMeter;

    @Column(name = "estimated_time", nullable = false)
    private LocalTime estimatedTime;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(name = "is_disable", nullable = false)
    private boolean isDisable;

    @Override
    public ServiceDto toResponseDto() {
        ServiceDto dto = new ServiceDto();
        dto.setId(id);
        dto.setName(name);
        dto.setType(type);
        dto.setDescription(description);
        dto.setPrice(price);
        dto.setTravelPricePerMeter(travelPricePerMeter);
        dto.setEstimatedTime(estimatedTime);
        dto.setCreatedAt(createdAt);
        dto.setUpdatedAt(updatedAt);
        dto.setDisable(isDisable);
        return dto;
    }
}


