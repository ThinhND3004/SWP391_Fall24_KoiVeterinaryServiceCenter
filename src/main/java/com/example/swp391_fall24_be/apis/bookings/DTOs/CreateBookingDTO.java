package com.example.swp391_fall24_be.apis.bookings.DTOs;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.bookings.BookingEntity;
import com.example.swp391_fall24_be.apis.bookings.MeetingMethodEnum;
import com.example.swp391_fall24_be.apis.bookings.StatusEnum;
import com.example.swp391_fall24_be.apis.services.ServiceEntity;
import com.example.swp391_fall24_be.core.IDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "Customer Id is required!")
    private String customerId;

    @NotBlank(message = "Veterian Id is required!")
    private String veterianId;

    @NotBlank(message = "Service Id is required!")
    private String serviceId;

    @NotNull(message = "Description must not be null!")
    private String description;

//    @NotNull(message = "Service Price is required!")
//    @Min(0)
//    private Float servicePrice;

//    @NotNull(message = "Travel Price is required!")
//    @Min(0)
//    private Float travelPrice;

    @NotBlank(message = "Destination is required!")
    private String destination;

    @NotNull(message = "Meeting method is required!")
    private MeetingMethodEnum meetingMethod;

    @NotNull(message = "Start At is required!")
    private LocalDateTime startAt;

    @Override
    public BookingEntity toEntity() {
        BookingEntity booking = new BookingEntity();

        AccountEntity customer = new AccountEntity();
        customer.setId(customerId);
        booking.setCustomer(customer);

        AccountEntity veterian = new AccountEntity();
        veterian.setId(customerId);
        booking.setVeterinarianIsBooked(veterian);

        ServiceEntity service = new ServiceEntity();
        service.setId(serviceId);
        booking.setService(service);

        booking.setDescription(description);
//        booking.setServicePrice(servicePrice);
//        booking.setTravelPrice(travelPrice);
        booking.setDestination(destination);
        booking.setMeetingMethodEnum(meetingMethod);
        booking.setStatusEnum(StatusEnum.UNPAID);
        booking.setDecline(false);
        booking.setStartedAt(startAt);

        return booking;
    }
}
