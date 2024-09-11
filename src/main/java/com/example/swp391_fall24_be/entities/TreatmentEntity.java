package com.example.swp391_fall24_be.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "treatments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NotBlank
public class TreatmentEntity {
    @Id
    private UUID id;

    @OneToMany
    @JoinColumn(name = "fish_id")
    private KoiFishEntity fishID;

    @OneToMany
    @JoinColumn(name = "pond_id")
    private PondEntity pondID;

    @Column(name = "prescription_id")
    private UUID prescriptionID;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "notes")
    private String notes;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
