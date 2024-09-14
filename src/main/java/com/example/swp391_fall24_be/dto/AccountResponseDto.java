package com.example.swp391_fall24_be.dto;

import com.example.swp391_fall24_be.entities.RoleEntity;
import lombok.Data;

import java.util.UUID;

@Data
public class AccountResponseDto {
    private UUID id;
    private String email;
    private RoleEntity role;
}
