package com.example.swp391_fall24_be.apis.vnpay;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreatePaymentDto {
    @NotNull(message = "Amount is required")
    private Long amount;

    @NotBlank(message = "Bank Code is required")
    private String bankCode;

}
