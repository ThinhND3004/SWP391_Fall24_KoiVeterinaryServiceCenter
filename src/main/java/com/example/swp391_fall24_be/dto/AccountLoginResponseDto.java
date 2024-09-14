package com.example.swp391_fall24_be.dto;

import com.example.swp391_fall24_be.core.ResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountLoginResponseDto extends ResponseDto<String> {
    private String token;
}
