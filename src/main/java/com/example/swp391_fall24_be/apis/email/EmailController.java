package com.example.swp391_fall24_be.apis.email;
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
    public ResponseEntity<?> sendInvitationForVeterinarian(@RequestParam String to,
                                                        @RequestParam String recipientName,
                                                        @RequestParam String serviceName,
                                                        @RequestParam String serviceMethod,
                                                        @RequestParam String date,
                                                        @RequestParam String time,
                                                        @RequestParam String location,
                                                        @RequestParam String referenceNumber,
                                                        @RequestParam String companyName,
                                                        @RequestParam String companyWebsite) {
        emailService.sendInvitationForVeterinarian(to, recipientName, serviceName, serviceMethod, date, time, location, referenceNumber, companyName, companyWebsite);
        return new ResponseEntity<>("Invitation for veterinarian email sent successfully!", HttpStatus.OK);
    }
}
