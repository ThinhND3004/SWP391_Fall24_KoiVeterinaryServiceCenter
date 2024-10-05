package com.example.swp391_fall24_be.apis.auth;

import com.example.swp391_fall24_be.apis.auth.dto.AccountLoginDto;
import com.example.swp391_fall24_be.apis.auth.dto.AccountLoginResponseDto;
import com.example.swp391_fall24_be.core.ProjectException;
import com.example.swp391_fall24_be.core.ResponseDto;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("login-password")
    @ApiResponse(description = "Login with username and password", headers = {
            @Header(name = "content-type", description = "application/json")
    })
    public ResponseDto<AccountLoginResponseDto> login(@RequestBody AccountLoginDto loginDto) {
        try {
            AccountLoginResponseDto response = authenticationService.loginWithUsernameAndPassword(loginDto);
            return new ResponseDto<>(HttpStatus.OK.value(), "Login successfully!", response, null);
        } catch (ProjectException e) {
            return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), "Cannot login!", null, e.getMessage());
        }
    }

    @GetMapping("/login-google")
    @ApiResponse(description = "Login with google", headers = {
            @Header(name = "content-type", description = "application/json")
    })
    public ResponseDto<AccountLoginResponseDto> loginWithGoogle(@RequestParam String credential) {
        try {
            AccountLoginResponseDto response = authenticationService.loginWithGoogle(credential);
            return new ResponseDto<>(HttpStatus.OK.value(), "Login with google successfully!", response, null);
        } catch (ProjectException e) {
            System.out.println(e.getMessage());
            return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), "Cannot login with google!", null, e.getMessage());
        }
    }
}
