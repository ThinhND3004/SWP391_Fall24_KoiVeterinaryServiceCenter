package com.example.swp391_fall24_be.apis.services.dtos;

import com.example.swp391_fall24_be.apis.services.ServiceEntity;
import com.example.swp391_fall24_be.apis.services.ServiceMeetingMethodEnum;
import com.example.swp391_fall24_be.apis.services.ServiceTypeEnum;
import com.example.swp391_fall24_be.apis.timetables.DTOs.TimetableTime;
import com.example.swp391_fall24_be.core.IDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalTime;

@Data
public class CreateServiceDto implements IDto<ServiceEntity> {
    @NotBlank(message = "Name is required!")
    @Size(max = 100, message = "Name must not pass 100 letters!")
    @JsonProperty("name")
    private String name;

    @NotNull(message = "Type is required!")
    @JsonProperty("type")
    private ServiceTypeEnum type;

    @NotBlank(message = "Overview is required!")
    @JsonProperty("overview")
    private String overview;

    @NotBlank(message = "Description is required!")
    @JsonProperty("description")
    private String description;

    @NotNull(message = "Meeting method is required!")
    @JsonProperty("meetingMethod")
    private ServiceMeetingMethodEnum meetingMethod;

    @NotNull(message = "Price is required!")
    @Min(value = 0, message = "Price must not be negative!")
    @JsonProperty("price")
    private Float price;

    @NotNull(message = "Travel price per meter is required!")
    @Min(value = 0, message = "Travel price per meter must not be negative!")
    @JsonProperty("travelPricePerMeter")
    private Float travelPricePerMeter;

    @NotNull(message = "Estimated Time is required!")
    @JsonProperty("estimatedTime")
    private TimetableTime estimatedTime;

    @Override
    public ServiceEntity toEntity() {
        ServiceEntity service = new ServiceEntity();
        service.setName(name);
        service.setType(type);
        service.setDescription(description);
        service.setMeetingMethod(meetingMethod);
        service.setPrice(price);
        service.setTravelPricePerMeter(travelPricePerMeter);
        service.setEstimatedTime(
                LocalTime.of(
                        estimatedTime.getHours(),
                        estimatedTime.getMinutes()
                )
        );
        service.setIsDisable(false);

        return service;
    }
}
