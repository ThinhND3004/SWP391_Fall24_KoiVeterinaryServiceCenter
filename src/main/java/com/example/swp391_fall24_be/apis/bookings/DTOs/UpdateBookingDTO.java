package com.example.swp391_fall24_be.apis.bookings.DTOs;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.bookings.BookingEntity;
import com.example.swp391_fall24_be.apis.bookings.StatusEnum;
import com.example.swp391_fall24_be.apis.services.ServiceEntity;
import com.example.swp391_fall24_be.core.IDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class UpdateBookingDTO implements IDto<BookingEntity> {

//    @NotBlank(message = "Additional Information is required!")
//    @Size(max = 255, message = "Description length must be between 1 and 255 characters")
//    private String additionalInformation;

//    @NotBlank(message = "Destination is required!")
//    @Size(max = 255, message = "User Address length must be less than 255 characters")
//    private String userAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusEnum statusEnum;

    @NotNull(message = "Start Date is required!")
    @FutureOrPresent(message = "Start Date must be in the future or present")
    private LocalDateTime startedAt;

    @Override
    public BookingEntity toEntity() {
        BookingEntity booking = new BookingEntity();
//        booking.setAdditionalInformation(additionalInformation);
//        booking.setUserAddress(userAddress);
        booking.setStatusEnum(statusEnum);
        booking.setStartedAt(startedAt);
        return booking;
    }
}