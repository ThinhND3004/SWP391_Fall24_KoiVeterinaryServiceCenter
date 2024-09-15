package com.example.swp391_fall24_be.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "shipping")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NotBlank
public class ShippingEntity {
    @Id
    private UUID id;

    @Column(name = "vehicle")
    private String vehicle;

    @Column(name = "price_per_meters")
    private float pricePerMeters;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
