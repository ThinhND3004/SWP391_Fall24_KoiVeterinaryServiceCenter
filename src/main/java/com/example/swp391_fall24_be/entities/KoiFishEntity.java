package com.example.swp391_fall24_be.entities;

import com.example.swp391_fall24_be.apis.accounts.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "koi_fishes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NotBlank
public class KoiFishEntity {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Account customerID;

    @ManyToOne
    @JoinColumn(name = "species_id")
    private KoiSpeciesEntity speciesID;

    @Column(name = "name")
    private String name;

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