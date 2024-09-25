package com.example.swp391_fall24_be.apis.services;

import com.example.swp391_fall24_be.apis.services.DTOs.ServiceDTO;
import com.example.swp391_fall24_be.core.IObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "services")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Service implements IObject<ServiceDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "id", nullable = false, columnDefinition = "UNIQUEIDENTIFIER")
    private UUID id;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(20)")
    private String name;

    @Column(name = "service_type", nullable = false, columnDefinition = "VARCHAR(20)")
    @Enumerated(EnumType.STRING)
    private ServiceTypeEnum serviceType;

    @Column(name = "description", nullable = false, columnDefinition = "VARCHAR(200)")
    private String description;

    @Column(name = "meeting_method", nullable = false, columnDefinition = "VARCHAR(20)")
    @Enumerated(EnumType.STRING)
    private MeetingMethodEnum meetingMethodEnum;

    @Column(name = "service_price", nullable = false, columnDefinition = "FLOAT")
    private float price;

    @Column(name = "travel_price_per_meters", nullable = false, columnDefinition = "FLOAT")
    private float travelPricePerMeters;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime updatedAt;

    @Column(name = "is_disabled", nullable = false, columnDefinition = "BOOLEAN")
    private boolean disable;

    @Override
    public ServiceDTO toResponseDto() {
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setId(id);
        serviceDTO.setName(name);
        serviceDTO.setDescription(description);
        serviceDTO.setServiceType(serviceType);
        serviceDTO.setMeetingMethod(meetingMethodEnum);
        serviceDTO.setTravelPricePerMeters(travelPricePerMeters);
        serviceDTO.setPrice(price);
        serviceDTO.setCreatedAt(createdAt);
        serviceDTO.setUpdatedAt(updatedAt);
        serviceDTO.setDisable(disable);
        return serviceDTO;
    }
}
