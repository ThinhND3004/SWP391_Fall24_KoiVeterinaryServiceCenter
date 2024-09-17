package com.example.swp391_fall24_be.apis.koifishes;

import com.example.swp391_fall24_be.apis.accounts.Account;
import com.example.swp391_fall24_be.apis.koifishes.dto.KoiFishDto;
import com.example.swp391_fall24_be.core.IObject;
import com.example.swp391_fall24_be.apis.koispecies.KoiSpecies;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class KoiFish implements IObject<KoiFishDto> {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Account customerID;

    @ManyToOne
    @JoinColumn(name = "species_id")
    private KoiSpecies speciesID;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @Column(name = "length")
    private float length;

    @Column(name = "width")
    private float width;

    @Column(name = "age_years")
    private int ageYears;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Override
    public KoiFishDto toResponseDto() {
        return null;
    }
}