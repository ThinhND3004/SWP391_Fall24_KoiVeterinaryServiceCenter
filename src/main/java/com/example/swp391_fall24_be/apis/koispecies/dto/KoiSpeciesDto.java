package com.example.swp391_fall24_be.apis.koispecies.dto;

import com.example.swp391_fall24_be.apis.accounts.Account;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class KoiSpeciesDto {
    private Account customerID;
    private String name;
    private LocalDateTime createdAt;
}
