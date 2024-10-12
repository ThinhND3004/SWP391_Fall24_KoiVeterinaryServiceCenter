package com.example.swp391_fall24_be.apis.vnpay.dtos;

import com.example.swp391_fall24_be.apis.vnpay.PaymentForEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CreatePaymentDto {
    @NotNull(message = "Payment for is required!")
    private PaymentForEnum payment;

    @NotNull(message = "Total Price is required!")
    @Min(value = 0, message = "Total Price must not be negative!")
    private Float totalPrice;
}
