package com.example.swp391_fall24_be.apis.email;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.bookings.BookingEntity;
import com.example.swp391_fall24_be.apis.bookings.BookingRepository;
import com.example.swp391_fall24_be.apis.email.DTOs.InvitationResultDto;
import com.example.swp391_fall24_be.apis.email.DTOs.ReportEmailDto;
import com.example.swp391_fall24_be.apis.email.DTOs.SendInvitationDto;
import com.example.swp391_fall24_be.apis.email.DTOs.SendOrderConfirmedDto;
import com.example.swp391_fall24_be.apis.gg_meeting.GoogleMeetService;
import com.example.swp391_fall24_be.apis.services.ServiceEntity;
import com.example.swp391_fall24_be.core.ResponseDto;
import freemarker.template.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.*;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String email;


    private final JavaMailSender javaMailSender;
    private final Configuration freemarkerConfig;


    private final GoogleMeetService meetService;

    private final BookingRepository bookingRepository;

    public EmailService(JavaMailSender javaMailSender, Configuration freemarkerConfig, GoogleMeetService meetService, BookingRepository bookingRepository) {
        this.javaMailSender = javaMailSender;
        this.freemarkerConfig = freemarkerConfig;
        this.meetService = meetService;
        this.bookingRepository = bookingRepository;
    }

    public void sendBothForCustomerAndVeterian(String bookingId){
        Optional<BookingEntity> findBookingResult = bookingRepository.findById(bookingId);
        if(findBookingResult.isPresent()){

        }
    }

    public void sendOrderConfirmationEmail(SendOrderConfirmedDto dto) {
        String subject = "Order Confirmation";

        Map<String, Object> variables = new HashMap<>();
        variables.put("name", dto.recipientName);
        variables.put("serviceName", dto.serviceName);
        variables.put("date", dto.date);
        variables.put("time", dto.time);
        variables.put("location", dto.location);
        variables.put("amount", dto.amount);
        variables.put("meetLink", dto.meetingLink);

//        String body = "Dear " + recipientName + ",\n\n"
//                + "Thank you for booking " + serviceName + "!\n\n"
//                + "Here are the details of your booking:\n"
//                + "Date: " + date + "\n"
//                + "Time: " + time + "\n"
//                + "Location: " + location + "\n"
//                + "Amount: " + amount + "\n"
//                + "If you have any questions or need to make changes to your booking, please contact us at " + companyPhone + " or visit our website at " + companyWebsite + ".\n\n"
//                + "Thank you for choosing " + companyName + "!\n\n"
//                + "Sincerely,\n"
//                + companyName + " Team";
        sendEmailWithTemplate(dto.to, subject, "order-confirmation.ftl",variables);
    }

    public void sendInvitationForVeterinarian(SendInvitationDto dto) {
        String subject = "Invitation for Veterinarian";
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", dto.recipientName);
        variables.put("serviceName", dto.serviceName);
        variables.put("serviceMethod", dto.serviceMethod);
        variables.put("date", dto.date);
        variables.put("time", dto.time);
        variables.put("location", dto.location);
        variables.put("meetingLink", dto.meetingLink.trim());

        sendEmailWithTemplate(dto.to, subject, "send-invitation.ftl", variables);
    }

    public boolean sendReportEmailToCustomer(ReportEmailDto dto) {
        Optional<BookingEntity> findBookingResult = bookingRepository.findById(dto.bookingId);
        if(findBookingResult.isPresent()){
            BookingEntity booking = findBookingResult.get();
            AccountEntity customer = booking.getCustomer();
            AccountEntity veterian = booking.getVeterian();
            ServiceEntity service = booking.getService();

            Map<String, Object> variables = new HashMap<>();
            variables.put("name", customer.getFirstName() + " " + customer.getLastName());
            variables.put("serviceName", service.getName());
            variables.put("startedAt", booking.getStartedAt());
            variables.put("veterianName", veterian.getFirstName() + " " +veterian.getLastName() );
            variables.put("companyWebsite", dto.companyWebsite);

            String subject = "Invitation for Veterinarian";

            sendEmailWithTemplate(customer.getEmail(), subject, "send-report.ftl",variables);
            return true;
        }
        return false;
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


        // Send email assign for
    }

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom(email);

        javaMailSender.send(message);
    }

    public ResponseDto<String> sendEmailWithTemplate(String to, String subject, String templateName, Map<String, Object> variables){
        MimeMessage message = javaMailSender.createMimeMessage();
        ResponseDto<String> response = new ResponseDto();
        System.out.println("Sending email to: "+to+", using template: "+templateName+", with variables: "+ variables.toString());

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED, "UTF-8");

            // Set email metadata
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(email);

            // Populate the template
            Template template = freemarkerConfig.getTemplate(templateName);
            String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, variables);

            // Set the email body
            helper.setText(htmlContent, true);

            // Send the email
            javaMailSender.send(message);

            response.setStatus(HttpStatus.OK.value());
            response.setData("Send email with template successful!");
            response.setMessage("Send email successful!");

        } catch (MessagingException | TemplateException | IOException e) {
            List<String> errorList = new ArrayList<>();
            errorList.add(e.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Send email failed!");
            response.setErr(errorList);
        }
        return response;
    }
}

