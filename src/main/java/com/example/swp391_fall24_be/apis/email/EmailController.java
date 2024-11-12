package com.example.swp391_fall24_be.apis.email;
import com.example.swp391_fall24_be.apis.email.DTOs.InvitationResultDto;
import com.example.swp391_fall24_be.apis.email.DTOs.ReportEmailDto;
import com.example.swp391_fall24_be.apis.email.DTOs.SendInvitationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-order-confirmation")
    public ResponseEntity<?> sendOrderConfirmation(@RequestParam String to,
                                                @RequestParam String recipientName,
                                                @RequestParam String serviceName,
                                                @RequestParam String date,
                                                @RequestParam String time,
                                                @RequestParam String location,
                                                @RequestParam String referenceNumber,
                                                @RequestParam String amount,
                                                @RequestParam String paymentMethod,
                                                @RequestParam String companyName,
                                                @RequestParam String companyPhone,
                                                @RequestParam String companyWebsite) {
        emailService.sendOrderConfirmationEmail(to, recipientName, serviceName, date, time, location, referenceNumber, amount, paymentMethod, companyName, companyPhone, companyWebsite);
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
}
