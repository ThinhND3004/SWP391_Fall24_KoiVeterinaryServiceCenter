package com.example.swp391_fall24_be.apis.vnpay.dtos;

import lombok.Data;

@Data
public class RefundPaymentDto {
    private String txnRef;      // Mã giao dịch (TxnRef)
    private long amount;        // Số tiền hoàn lại (Amount)
    private String orderInfo;   // Thông tin đơn hàng (Order Info)
    private int transactionType;
}
