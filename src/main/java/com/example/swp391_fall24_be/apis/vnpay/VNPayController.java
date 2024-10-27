package com.example.swp391_fall24_be.apis.vnpay;

import com.example.swp391_fall24_be.apis.vnpay.dtos.CreatePaymentDto;
import com.example.swp391_fall24_be.config.VNPayConfiguration;
import com.example.swp391_fall24_be.core.ResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vnpay")
public class VNPayController {
    @Autowired
    private VNPayService service;

    @PostMapping("/create-payment")
    public ResponseDto<?> createPayment(
            HttpServletRequest request,
            @RequestBody CreatePaymentDto dto
    ) {
        try {
            String IpAddress = VNPayConfiguration.getIpAddress(request);
            return new ResponseDto<>(
                    HttpStatus.OK.value(),
                    "Get VNP ReturnUrl successful!",
                    service.returnPaymentUrl(dto, IpAddress),
                    null
            );
        }
        catch (Exception e){
            List<String> errorList = new ArrayList<>();
            errorList.add(e.getMessage());
            return new ResponseDto<>(
                    HttpStatus.OK.value(),
                    "Cannot get VNP ReturnUrl successful!",
                    null,
                    errorList
            );
        }
    }

    @GetMapping("/return-url")
    public ResponseDto<Map<String, Object>> handleReturnUrl(HttpServletRequest request) {
        String vnp_ResponseCode = request.getParameter("vnp_ResponseCode");
        String orderId = request.getParameter("vnp_TxnRef");
        String amount = request.getParameter("vnp_Amount");

        String message = vnp_ResponseCode.equals("00") ? "Giao dịch thành công" : "Giao dịch không thành công";

        Map<String, Object> transactionDetails = new HashMap<>();
        transactionDetails.put("vnp_ResponseCode", vnp_ResponseCode);
        transactionDetails.put("orderId", orderId);
        transactionDetails.put("amount", amount);

        return new ResponseDto<>(
                HttpStatus.OK.value(),
                message,
                transactionDetails,
                null
        );
    }
}