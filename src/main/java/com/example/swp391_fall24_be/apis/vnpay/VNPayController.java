package com.example.swp391_fall24_be.apis.vnpay;

import com.example.swp391_fall24_be.apis.vnpay.dtos.CreatePaymentDto;
import com.example.swp391_fall24_be.apis.vnpay.dtos.RefundPaymentDto;
import com.example.swp391_fall24_be.config.VNPayConfiguration;
import com.example.swp391_fall24_be.core.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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


    @PostMapping("/refund-payment")
    public ResponseDto<?> refundPayment(
            HttpServletRequest request,
            @RequestBody RefundPaymentDto dto
    ) {
        try {
            // Lấy địa chỉ IP của máy chủ thực hiện gọi API
            String ipAddress = VNPayConfiguration.getIpAddress(request);
            VNPayConfiguration vnPayConfiguration = new VNPayConfiguration();

            // Tạo tham số cho yêu cầu hoàn tiền
            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_RequestId", UUID.randomUUID().toString());
            vnp_Params.put("vnp_Version", vnPayConfiguration.getVnp_Version());
            vnp_Params.put("vnp_Command", "refund");
            vnp_Params.put("vnp_TmnCode", vnPayConfiguration.getVnp_TmnCode());
            vnp_Params.put("vnp_TransactionType", String.valueOf(dto.getTransactionType()));
            vnp_Params.put("vnp_TxnRef", dto.getTxnRef());
            vnp_Params.put("vnp_Amount", String.valueOf(dto.getAmount() * 100)); // Số tiền (VND)
            vnp_Params.put("vnp_OrderInfo", "Refund order: " + dto.getOrderInfo());
            vnp_Params.put("vnp_TransactionNo", String.valueOf(1));

            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String vnp_TransactionDate = formatter.format(calendar.getTime());
            vnp_Params.put("vnp_TransactionDate", vnp_TransactionDate);
            vnp_Params.put("vnp_CreateBy", "Your Name");

            String vnp_CreateDate = formatter.format(calendar.getTime());
            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
            vnp_Params.put("vnp_IpAddr", ipAddress);

            // Tạo chuỗi dữ liệu để tính checksum theo đúng quy định của VNPay
            String data = String.join("|",
                    vnp_Params.get("vnp_RequestId"),
                    vnp_Params.get("vnp_Version"),
                    vnp_Params.get("vnp_Command"),
                    vnp_Params.get("vnp_TmnCode"),
                    vnp_Params.get("vnp_TransactionType"),
                    vnp_Params.get("vnp_TxnRef"),
                    vnp_Params.get("vnp_Amount"),
                    vnp_Params.get("vnp_TransactionNo"),
                    vnp_Params.get("vnp_TransactionDate"),
                    vnp_Params.get("vnp_CreateBy"),
                    vnp_Params.get("vnp_CreateDate"),
                    vnp_Params.get("vnp_IpAddr"),
                    vnp_Params.get("vnp_OrderInfo")
            );

            // Tính checksum sử dụng HMAC SHA-512
            String checksum = vnPayConfiguration.hmacSHA512(vnPayConfiguration.getSecretKey(), data);
            vnp_Params.put("vnp_SecureHash", checksum);

            // Tạo request body cho POST request dưới dạng JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(vnp_Params);

            // Gửi POST request đến VNPay API
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(
                    "https://sandbox.vnpayment.vn/merchant_webapi/api/transaction",
                    entity,
                    String.class
            );

            // Trả về kết quả từ VNPay
            return new ResponseDto<>(HttpStatus.OK.value(), "Refund request processed successfully", response.getBody(), null);
        } catch (Exception e) {
            // Xử lý lỗi nếu có
            List<String> errorList = new ArrayList<>();
            errorList.add(e.getMessage());
            return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred while processing the refund", null, errorList);
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