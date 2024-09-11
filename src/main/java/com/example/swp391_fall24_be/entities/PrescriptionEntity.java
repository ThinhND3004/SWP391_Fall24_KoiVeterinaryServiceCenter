package com.example.swp391_fall24_be.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "prescriptions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NotBlank
public class PrescriptionEntity {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private MedicineEntity medicineID;

    @ManyToMany
    @JoinColumn(name = "shipping_id")
    private ShippingEntity shippingID;

    @Column(name = "total_price")
    private float totalPrice;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
