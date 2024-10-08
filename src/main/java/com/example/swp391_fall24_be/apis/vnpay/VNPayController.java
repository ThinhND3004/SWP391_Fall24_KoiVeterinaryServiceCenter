package com.example.swp391_fall24_be.apis.vnpay;

import com.example.swp391_fall24_be.apis.vnpay.dtos.CreatePaymentDto;
import com.example.swp391_fall24_be.config.VNPayConfiguration;
import com.example.swp391_fall24_be.core.ResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vnpay")
public class VNPayController {
    @Autowired
    private VNPayService service;

    @PostMapping("/create-payment/{orderId}")
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
            return new ResponseDto<>(
                    HttpStatus.OK.value(),
                    "Cannot get VNP ReturnUrl successful!",
                    null,
                    e.getMessage()
            );
        }
    }
    @GetMapping("/return-url")
    public ResponseDto<String> handleReturnUrl(
            HttpServletRequest request
    ) {
        String vnp_ResponseCode = request.getParameter("vnp_ResponseCode");
        String message = vnp_ResponseCode.equals("00") ? "GD thanh cong" : "GD khong thanh cong";
        return new ResponseDto<>(
                HttpStatus.OK.value(),
                message,
                vnp_ResponseCode,
                null
        );
    }
}
