package com.example.swp391_fall24_be.apis.auth;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.accounts.AccountRoleEnum;
import com.example.swp391_fall24_be.apis.accounts.dtos.AccountDto;
import com.example.swp391_fall24_be.apis.auth.dto.AccountLoginDto;
import com.example.swp391_fall24_be.apis.auth.dto.AccountLoginResponseDto;
import com.example.swp391_fall24_be.core.ProjectException;
import com.example.swp391_fall24_be.core.ResponseDto;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("login-password")
    @ApiResponse(description = "Login with username and password", headers = {
            @Header(name = "content-type", description = "application/json")
    })
    public ResponseDto<AccountLoginResponseDto> login(@RequestBody AccountLoginDto loginDto) {
        try {
            AccountLoginResponseDto response = authenticationService.loginWithUsernameAndPassword(loginDto);
            return new ResponseDto<>(HttpStatus.OK.value(), "Login successfully!", response, null);
        } catch (ProjectException e) {
            List<String> errorList = new ArrayList<>();
            errorList.add(e.getMessage());
            return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), "Cannot login!", null, errorList);
        }
    }

//    @GetMapping("/login-google")
//    @ApiResponse(description = "Login with google", headers = {
//            @Header(name = "content-type", description = "application/json")
//    })
//    public ResponseDto<AccountLoginResponseDto> loginWithGoogle(@RequestParam String credential) {
//        try {
//            AccountLoginResponseDto response = authenticationService.loginWithGoogle(credential);
//            return new ResponseDto<>(HttpStatus.OK.value(), "Login with google successfully!", response, null);
//        } catch (ProjectException e) {
//            List<String> errorList = new ArrayList<>();
//            errorList.add(e.getMessage());
//            Logger.getAnonymousLogger().info("Error: " + e.getMessage());
//            return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), "Cannot login with google!", null, errorList);
//        }
//    }

    @GetMapping("/login-google")
    public ResponseDto<AccountLoginResponseDto> loginWithGoogle(@RequestParam String credential) {
        try {
            AccountLoginResponseDto token = authenticationService.loginWithGoogle(credential);

            return new ResponseDto<>(HttpStatus.OK.value(), "Login with Google successfully!", token, null);
        } catch (Exception e) {
            List<String> errorList = new ArrayList<>();
            errorList.add(e.getMessage());
            return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), "Cannot login with Google!", null, errorList);
        }
    }


}
