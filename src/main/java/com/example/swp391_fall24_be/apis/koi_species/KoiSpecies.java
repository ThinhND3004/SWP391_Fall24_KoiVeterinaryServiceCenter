package com.example.swp391_fall24_be.apis.koi_species;

import com.example.swp391_fall24_be.apis.koi_fishes.KoiFish;
import com.example.swp391_fall24_be.entities.KoiFishEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "koi_species")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KoiSpecies {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "koi_id")
    private KoiFish koiFish;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
