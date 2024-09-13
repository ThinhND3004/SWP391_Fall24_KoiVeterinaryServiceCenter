package com.example.swp391_fall24_be.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Entity(name = "bookings")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NotBlank
public class BookingEntity {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private UserEntity customerId;

    @ManyToOne
    @JoinColumn(name = "veterian_id")
    private UserEntity veterianId;

    @Column(name = "description")
    private String description;

    @Column(name = "total_price")
    private float totalPrice;
}
