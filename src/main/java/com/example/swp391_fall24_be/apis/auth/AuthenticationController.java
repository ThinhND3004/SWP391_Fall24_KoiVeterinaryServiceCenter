package com.example.swp391_fall24_be.apis.auth;

import com.example.swp391_fall24_be.apis.auth.dto.AccountLoginDto;
import com.example.swp391_fall24_be.apis.auth.dto.AccountLoginResponseDto;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @SneakyThrows
    @PostMapping("login-password")
    public ResponseEntity<AccountLoginResponseDto> login(@RequestBody AccountLoginDto loginDto) {
        AccountLoginResponseDto response = authenticationService.loginWithUsernameAndPassword(loginDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");
        return ResponseEntity.ok().headers(headers).body(response);
    }

    @GetMapping("/login-google")
    public ResponseEntity<AccountLoginResponseDto> loginWithGoogle(@RequestParam String credential) {
        AccountLoginResponseDto response = authenticationService.loginWithGoogle(credential);
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");
        return ResponseEntity.ok().headers(headers).body(response);
    }
}
