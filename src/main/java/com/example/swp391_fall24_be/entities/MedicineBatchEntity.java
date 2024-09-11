package com.example.swp391_fall24_be.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "medicine_batches")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NotBlank
public class MedicineBatchEntity {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private MedicineEntity medicineID;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "received_at")
    private LocalDateTime receivedAt;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;
}
