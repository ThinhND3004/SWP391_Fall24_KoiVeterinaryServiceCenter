package com.example.swp391_fall24_be.apis.bookings.DTOs;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.bookings.BookingEntity;
import com.example.swp391_fall24_be.apis.bookings.MeetingMethodEnum;
import com.example.swp391_fall24_be.apis.bookings.StatusEnum;
import com.example.swp391_fall24_be.apis.services.ServiceEntity;
import com.example.swp391_fall24_be.core.IDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class UpdateBookingDTO implements IDto<BookingEntity> {
//    @NotBlank(message = "CustomerID is required!")
//    private String customerId;

    @NotBlank(message = "VeterinarianID is required!")
    @Schema(description = "ID of the veterinarian", example = "4e46b3a9-bf00-4e65-8474-a7b1606bbb60")
    private String veterinarianId;

    @NotBlank(message = "ServiceID is required!")
    @Schema(description = "ID of the service", example = "b8eeba87-692e-4894-8457-ba4227ff7399")
    private String serviceId;

//    @NotNull(message = "Additional Information must not be null!")
    @Schema(description = "Additional information about the booking", example = "This is an example of additional information.")
    private String additionalInformation;

    @NotBlank(message = "Address is required!")
    @Schema(description = "Address of the customer", example = "123 Main St, City, Country")
    private String userAddress;

    @NotNull(message = "Distance is required!")
    @Schema(description = "Distance in kilometers", example = "15.0")
    private Float distanceKilometers;

    @NotNull(message = "Meeting method is required!")
    @Schema(description = "Method of the meeting", example = "ONLINE")
    private MeetingMethodEnum meetingMethod;

    @NotNull(message = "Start At is required!")
    @Schema(description = "Start time of the booking", example = "2024-10-21T16:49:37")
    private LocalDateTime startAt;

    @Override
    public BookingEntity toEntity() {
        BookingEntity booking = new BookingEntity();

//        AccountEntity customer = new AccountEntity();
//        customer.setId(customerId);
//        booking.setCustomer(customer);

        AccountEntity veterian = new AccountEntity();
        veterian.setId(veterinarianId);
        booking.setVeterinarianIsBooked(veterian);

        ServiceEntity service = new ServiceEntity();
        service.setId(serviceId);
        booking.setService(service);

        booking.setAdditionalInformation(additionalInformation);

        booking.setDistanceKilometers(distanceKilometers);
        booking.setUserAddress(userAddress);
        booking.setMeetingMethodEnum(meetingMethod);
        booking.setStatusEnum(StatusEnum.UNPAID);
        booking.setIsDecline(false);
        booking.setStartedAt(startAt);
        return booking;
    }
}
