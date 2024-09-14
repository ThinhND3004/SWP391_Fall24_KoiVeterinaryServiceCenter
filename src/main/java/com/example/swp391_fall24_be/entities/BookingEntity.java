package com.example.swp391_fall24_be.entities;

import com.example.swp391_fall24_be.apis.accounts.Account;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity(name = "bookings")
@Data
public class BookingEntity {
    @Id
    private UUID id;
 // test
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
}
