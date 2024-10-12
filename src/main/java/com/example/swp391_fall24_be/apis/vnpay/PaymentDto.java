package com.example.swp391_fall24_be.apis.vnpay;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaymentDto implements Serializable {
    private String status;
    private String message;
    private String URL;
}
