package com.example.swp391_fall24_be.apis.koispecies.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class KoiSpeciesDto {
    private String id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
}
