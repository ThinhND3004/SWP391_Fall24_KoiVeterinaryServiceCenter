package com.example.swp391_fall24_be.apis.email;

import com.example.swp391_fall24_be.apis.email.DTOs.InvitationResultDto;
import com.example.swp391_fall24_be.apis.email.DTOs.SendInvitationDto;
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
        String subject = "Order Confirmation";
        String body = "Dear " + recipientName + ",\n\n"
                + "Thank you for booking " + serviceName + "!\n\n"
                + "Here are the details of your booking:\n"
                + "Date: " + date + "\n"
                + "Time: " + time + "\n"
                + "Location: " + location + "\n"
                + "Reference Number: " + referenceNumber + "\n"
                + "Amount: " + amount + "\n"
                + "Payment Method: " + paymentMethod + "\n\n"
                + "If you have any questions or need to make changes to your booking, please contact us at " + companyPhone + " or visit our website at " + companyWebsite + ".\n\n"
                + "Thank you for choosing " + companyName + "!\n\n"
                + "Sincerely,\n"
                + companyName + " Team";
        sendEmail(to, subject, body);
    }

    public void sendInvitationForVeterinarian(SendInvitationDto dto) {
        String subject = "Invitation for Veterinarian";
        String body = "Dear " + dto.recipientName + ",\n\n"
                + "You have been invited to provide " + dto.serviceName + " " +  dto.serviceMethod + "!\n\n"
                + "Here are the details of the appointment:\n"
                + "Date: " + dto.date + "\n"
                + "Time: " + dto.time + "\n"
                + "Location: " + dto.location + "\n"
                + "Reference Number: " + dto.referenceNumber + "\n"
                + "For accept or decline the invitation, please go to " + dto.companyWebsite + ".\n\n"
                + "Sincerely,\n"
                + dto.companyName + " Team";
        sendEmail(dto.to, subject, body);
    }

        public void sendInvitationResultForStaff(InvitationResultDto dto) {
        String invitationResult = dto.isAccepted ? "accepted" : "rejected";

        String subject = "Invitation Result";
        String body = "Dear " + dto.recipientName + ",\n\n"
                + dto.veterianName + " has " +invitationResult + " your invitation of booking " + dto.bookingId +"\n"
                + "at : " + dto.dateTime + "\n\n"
                + "Sincerely,\n"
                + dto.companyName + " Team";
        sendEmail(dto.to, subject, body);
    }

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom(email);

        javaMailSender.send(message);
    }
}

