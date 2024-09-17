package com.example.swp391_fall24_be.entities;

import com.example.swp391_fall24_be.apis.accounts.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Data;
import java.util.UUID;


@Entity(name = "bookings")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookingEntity {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Account customerId;

    @ManyToOne
    @JoinColumn(name = "veterian_id")
    private Account veterianId;

    @Column(name = "description")
    private String description;

    @Column(name = "total_price")
    private float totalPrice;

    @Column(name = "status")
    private String status;
}
