package com.example.swp391_fall24_be.apis.koi_species.DTOs;

import com.example.swp391_fall24_be.entities.KoiFishEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class KoiSpeciesDTO {
    private UUID id;
    private KoiFishEntity koiFish;
    private String name;
    private LocalDateTime createdAt;
}
