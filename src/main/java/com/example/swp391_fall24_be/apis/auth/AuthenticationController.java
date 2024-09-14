package com.example.swp391_fall24_be.apis.auth;

import com.example.swp391_fall24_be.apis.auth.dto.AccountLoginDto;
import com.example.swp391_fall24_be.apis.auth.dto.AccountLoginResponseDto;
import com.example.swp391_fall24_be.core.ProjectException;
import com.example.swp391_fall24_be.core.ResponseDto;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping("login-password")
    @ApiResponse(description = "Login with username and password", headers = {
            @Header(name = "content-type", description = "application/json")
    })
    public ResponseDto<AccountLoginResponseDto> login(@RequestBody AccountLoginDto loginDto) {
        try {
            AccountLoginResponseDto response = authenticationService.loginWithUsernameAndPassword(loginDto);
            return new ResponseDto<>(HttpStatus.OK, "Login successfully!", response, null);
        } catch (ProjectException e) {
            return new ResponseDto<>(HttpStatus.BAD_REQUEST, "Cannot login!", null, e.getMessage());
        }
    }

    @GetMapping("/login-google")
    @ApiResponse(description = "Login with google", headers = {
            @Header(name = "content-type", description = "application/json")
    })
    public ResponseDto<AccountLoginResponseDto> loginWithGoogle(@RequestParam String credential) {
        try {
            AccountLoginResponseDto response = authenticationService.loginWithGoogle(credential);
            return new ResponseDto<>(HttpStatus.OK, "Login with google successfully!", response, null);
        } catch (ProjectException e) {
            System.out.println(e.getMessage());
            return new ResponseDto<>(HttpStatus.BAD_REQUEST, "Cannot login with google!", null, e.getMessage());
        }
    }
}