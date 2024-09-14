package com.example.swp391_fall24_be.apis.auth;

import com.example.swp391_fall24_be.core.ResponseDto;
import com.example.swp391_fall24_be.dto.AccountCreateDto;
import com.example.swp391_fall24_be.dto.AccountLoginResponseDto;
import com.example.swp391_fall24_be.service.AuthService;
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
    private final AuthService authService;

    @SneakyThrows
    @PostMapping("login-password")
    public ResponseEntity<ResponseDto<String>> login(@RequestBody AccountCreateDto loginDto) {
        var response = authService.loginWithUsernameAndPassword(loginDto);
        var headers = new HttpHeaders();
        headers.add("content-type", "application/json");
        return ResponseEntity.ok().headers(headers).body(response);
    }

    @GetMapping("/login-google")
    public ResponseEntity<AccountLoginResponseDto> loginWithGoogle(@RequestParam String credential) {
        AccountLoginResponseDto response = null;
        response = authService.loginWithGoogle(credential);
        var headers = new HttpHeaders();
        headers.add("content-type", "application/json");
        return ResponseEntity.ok().headers(headers).body(response);
    }
}
