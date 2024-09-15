package com.example.swp391_fall24_be.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;

@Component
public class CryptoUtils {

    @Value("${secrets.HMAC_SECRET}")
    private String hMac;

    public String crypto(String input) {
        try {
            var secretKeySpec = new SecretKeySpec(hMac.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            var mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);
            var hmac = mac.doFinal(input.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hmac);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean verify(String input, String providedHmac) {
        var computedHmac = crypto(input);
        return Objects.equals(computedHmac, providedHmac);
    }
}