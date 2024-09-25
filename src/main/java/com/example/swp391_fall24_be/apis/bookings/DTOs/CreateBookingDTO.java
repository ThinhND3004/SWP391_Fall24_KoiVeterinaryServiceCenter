package com.example.swp391_fall24_be.apis.bookings.DTOs;

import com.example.swp391_fall24_be.apis.bookings.Booking;
import com.example.swp391_fall24_be.apis.bookings.StatusEnum;
import com.example.swp391_fall24_be.core.IDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
public class CreateBookingDTO implements IDto<Booking> {

    @NotBlank(message = "Description is required!")
    @Size(max = 255, message = "Description length must be between 1 and 255 characters")
    private String description;

    @NotBlank(message = "Price is required!")
    @Min(value = 0, message = "Price must be a positive number")
    private Float totalPrice;

    @NotBlank(message = "Destination is required!")
    @Size(max = 255, message = "Destination length must be between 1 and 255 characters")
    private String destination;

    @Enumerated(EnumType.STRING)
    private StatusEnum statusEnum;

    @NotBlank(message = "Start Date is required!")
    private LocalDateTime startedAt;

    @NotBlank(message = "End Date is required!")
    private LocalDateTime endedAt;

    @Override
    public Booking toEntity() {
        Booking booking = new Booking();
        booking.setDescription(description);
        booking.setTotalPrice(totalPrice);
        booking.setDestination(destination);
        booking.setStatusEnum(statusEnum);
        booking.setStartedAt(startedAt);
        booking.setEndedAt(endedAt);
        return booking;
    }
}
