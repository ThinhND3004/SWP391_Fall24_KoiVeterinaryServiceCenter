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

@Entity(name = "koi_fish")
@NotBlank
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KoiFish {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "customer_id")
    private UUID customer_id;

    @Column(name = "name")
    private String name;

    @Column(name = "species")
    private String species;

    @Column(name = "color")
    private String color;

    @Column(name = "length")
    private float length;

    @Column(name = "width")
    private float width;

    @Column(name = "ageYears")
    private int ageYears;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
}
