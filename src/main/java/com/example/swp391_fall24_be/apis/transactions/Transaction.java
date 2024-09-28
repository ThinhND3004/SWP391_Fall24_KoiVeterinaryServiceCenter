package com.example.swp391_fall24_be.apis.transactions;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.bookings.Booking;
import com.example.swp391_fall24_be.apis.bookings.DTOs.BookingDTO;
import com.example.swp391_fall24_be.apis.bookings.StatusEnum;
import com.example.swp391_fall24_be.apis.prescription.PrescriptionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@Entity(name = "transactions")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Changed to Long

    @JoinColumn(name = "customer_id") // Specify the foreign key column
    @OneToOne(optional = false)
    private AccountEntity customer;

    @JoinColumn(name = "booking_id") // Specify the foreign key column
    @OneToOne
    private Booking booking;

    @JoinColumn(name = "prescription_id") // Specify the foreign key column
    @OneToOne
    private PrescriptionEntity prescriptions;

    @Enumerated(EnumType.STRING) // Enum handling
    private PaymentMethodEnum paymentMethod;

    @Column
    @CreatedDate
    private LocalDateTime transactionDate;

    @Column
    @LastModifiedDate
    private StatusEnum status;

    @PrePersist
    public void onPrePersist() {
        this.transactionDate = LocalDateTime.now(); // Set current time
    }
}
