package com.example.swp391_fall24_be.apis.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String email;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendOrderConfirmationEmail(String to, String recipientName, String serviceName, String date, String time, String location, String referenceNumber, String amount, String paymentMethod, String companyName, String companyPhone, String companyWebsite) {
        String subject = "Order Confirmation for Your Recent Booking";

        String body = "Dear " + recipientName + ",\n\n" +
                "I hope this message finds you well.\n\n" +
                "We are pleased to confirm your order for the " + serviceName + " scheduled on " + date + " at " + time + ". Below are the details of your booking:\n\n" +
                "Service Details:\n\n" +
                "Service: " + serviceName + "\n" +
                "Date: " + date + "\n" +
                "Time: " + time + "\n" +
                "Location: " + location + "\n" +
                "Booking Reference: " + referenceNumber + "\n\n" +
                "Payment Information:\n\n" +
                "Total Amount: " + amount + "\n" +
                "Payment Method: " + paymentMethod + "\n\n" +
                "If you have any questions or require further assistance, please do not hesitate to reach out to us at [contact email/phone number].\n\n" +
                "Thank you for choosing " + companyName + ". We look forward to serving you!\n\n" +
                "Best regards,\n" +
                "[Your Name]\n" +
                "[Your Position]\n" +
                companyName + "\n" +
                companyPhone + "\n" +
                companyWebsite;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom(email);

        javaMailSender.send(message);
    }
}

