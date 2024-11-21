package com.example.swp391_fall24_be.apis.gg_meeting;

import com.example.swp391_fall24_be.apis.gg_meeting.DTOs.CreateMeetDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/meetings")
@RequiredArgsConstructor
public class GoogleMeetController {

    private final GoogleMeetService googleMeetService;

    @PostMapping
    public ResponseEntity<String> createMeeting(@RequestBody() CreateMeetDto dto) throws Exception {
        String link = googleMeetService.createGoogleMeet(dto);
        return ResponseEntity.ok(link);
    }

    @GetMapping("/authorize")
    public ResponseEntity<String> authorize(HttpServletResponse response) throws IOException, IOException, GeneralSecurityException {
        String authorizationUrl = googleMeetService.getAuthorizationUrl();
        System.out.println("TEST: " + authorizationUrl);
//        response.sendRedirect(authorizationUrl);
        return ResponseEntity.ok(authorizationUrl);
    }

    // Endpoint để xử lý OAuth2 callback
    // OAuth2 Callback nhận mã xác thực từ Google
    @GetMapping("/oauth2callback")
    public ResponseEntity<String> oauth2Callback(@RequestParam("code") String code) {
        try {
            // Xử lý mã xác thực để lấy access token hoặc lưu trữ theo yêu cầu
            String accessToken = googleMeetService.getAccessToken(code);
            return ResponseEntity.ok("Authorization successful. Access Token: " + accessToken);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing OAuth2 callback: " + e.getMessage());
        }
    }

}
