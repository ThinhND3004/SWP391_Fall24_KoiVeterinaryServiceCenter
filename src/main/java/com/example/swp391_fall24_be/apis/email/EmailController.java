package com.example.swp391_fall24_be.apis.email;
import com.example.swp391_fall24_be.apis.email.DTOs.InvitationResultDto;
import com.example.swp391_fall24_be.apis.email.DTOs.ReportEmailDto;
import com.example.swp391_fall24_be.apis.email.DTOs.SendInvitationDto;
import com.example.swp391_fall24_be.apis.email.DTOs.SendOrderConfirmedDto;
import com.example.swp391_fall24_be.apis.services.ServicesService;
import com.example.swp391_fall24_be.core.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private ServicesService servicesService;

    @PostMapping("/send-order-confirmation")
    public ResponseEntity<?> sendOrderConfirmation(@RequestBody SendOrderConfirmedDto dto) {
        emailService.sendOrderConfirmationEmail(dto);
        return new ResponseEntity<>("Order confirmation email sent successfully!", HttpStatus.OK);
    }

    @PostMapping("/send-invitation-for-veterinarian")
    public ResponseEntity<?> sendInvitationForVeterinarian(@RequestBody SendInvitationDto dto) {
        emailService.sendInvitationForVeterinarian(dto);
        return new ResponseEntity<>("Invitation for veterinarian email sent successfully!", HttpStatus.OK);
    }

    @PostMapping("/send-invitation-result-for-staff")
    public ResponseEntity<?> sendInvitationResultForStaff(@RequestBody InvitationResultDto dto) {
        emailService.sendInvitationResultForStaff(dto);
        return new ResponseEntity<>("Invitation result for staff email sent successfully!", HttpStatus.OK);
    }

    @PostMapping("/send-report-mail-to-customer")
    public ResponseEntity<?> sendReportMailToCustomer(@RequestBody ReportEmailDto dto) {
        boolean result = emailService.sendReportEmailToCustomer(dto);
        if(result) return new ResponseEntity<>("Report email is sent successfully!", HttpStatus.OK);
        else return  new ResponseEntity<>("Report email is sent failed!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/test-send-template-email")
    public ResponseDto<?> sendTestEmail(@RequestBody String email) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", "John Doe");
        variables.put("location", "Vietnam");
        return emailService.sendEmailWithTemplate(email, "Test email with template",
                "email-template.ftl"
                ,variables);

    }
}
