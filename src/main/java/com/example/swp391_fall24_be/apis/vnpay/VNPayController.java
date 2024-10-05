package com.example.swp391_fall24_be.apis.vnpay;

import com.example.swp391_fall24_be.config.VNPayConfiguration;
import com.example.swp391_fall24_be.core.ResponseDto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/vnpay")
public class VNPayController {
    @PostMapping("/create-payment")
    public ResponseEntity<?> createPayment(
//            @RequestBody CreatePaymentDto dto
    ) throws UnsupportedEncodingException {

        String orderType = "other";
//        long amount = Integer.parseInt(req.getParameter("amount"))*100;
        String bankCode = "NCB";

        long amount = 100000;

        String vnp_TxnRef = VNPayConfiguration.getRandomNumber(8);
//        String vnp_IpAddr = VNPayConfiguration.getIpAddress(req);
        String vnp_TmnCode = VNPayConfiguration.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", VNPayConfiguration.vnp_Version);
        vnp_Params.put("vnp_Command", VNPayConfiguration.vnp_Command);
        vnp_Params.put("vnp_TmnCode", VNPayConfiguration.vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_BankCode", "NCB");

        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        vnp_Params.put("vnp_ReturnUrl", VNPayConfiguration.vnp_ReturnUrl);
//        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VNPayConfiguration.hmacSHA512(VNPayConfiguration.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VNPayConfiguration.vnp_PayUrl + "?" + queryUrl;

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setStatus("OK");
        paymentDto.setMessage("Successful!");
        paymentDto.setURL(paymentUrl);

//        return new ResponseDto<>(
//                HttpStatus.OK.value(),
//                "Payment successful!",
//                paymentDto,
//                null
//        );

        return ResponseEntity.status(HttpStatus.OK).body(paymentDto);

//        com.google.gson.JsonObject job = new JsonObject();
//        job.addProperty("code", "00");
//        job.addProperty("message", "success");
//        job.addProperty("data", paymentUrl);
//        Gson gson = new Gson();
//        resp.getWriter().write(gson.toJson(job));
    }
}
