package com.example.swp391_fall24_be.apis.bookings.DTOs;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.bookings.BookingEntity;
import com.example.swp391_fall24_be.apis.bookings.MeetingMethodEnum;
import com.example.swp391_fall24_be.apis.bookings.StatusEnum;
import com.example.swp391_fall24_be.apis.services.ServiceEntity;
import com.example.swp391_fall24_be.core.IDto;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateBookingDTO implements IDto<BookingEntity> {

    @Schema(hidden = true)
    private AccountEntity customer;

    @NotBlank(message = "Veterian Id is required!")
    private String veterianId;

    @NotBlank(message = "Service Id is required!")
    private String serviceId;

    @NotNull(message = "Description must not be null!")
    private String description;

    @NotNull(message = "Service Price is required!")
    @Min(0)
    private Float servicePrice;

    @NotNull(message = "Travel Price is required!")
    @Min(0)
    private Float travelPrice;

    @NotNull(message = "Distance Meters is required!")
    @Min(0)
    private Float distanceMeters;

    @NotBlank(message = "Destination is required!")
    private String userAddress;

    @NotNull(message = "Meeting method is required!")
    private MeetingMethodEnum meetingMethod;

    @NotNull(message = "Start At is required!")
    private LocalDateTime startAt;

    @Override
    public BookingEntity toEntity() {
        BookingEntity booking = new BookingEntity();

        AccountEntity veterian = new AccountEntity();
        veterian.setId(veterianId);
        booking.setVeterian(veterian);

        ServiceEntity service = new ServiceEntity();
        service.setId(serviceId);
        booking.setService(service);

        booking.setDescription(description);
        booking.setServicePrice(servicePrice);
        booking.setTravelPrice(travelPrice);
        booking.setDistanceMeters(distanceMeters);
        booking.setUserAddress(userAddress);
        booking.setMeetingMethodEnum(meetingMethod);
        booking.setStatusEnum(StatusEnum.UNPAID);
        booking.setDecline(false);
        booking.setStartedAt(startAt);

        return booking;
    }
}
