package com.example.swp391_fall24_be.apis.transactions;

import com.example.swp391_fall24_be.apis.accounts.Account;
import com.example.swp391_fall24_be.apis.bookings.Booking;
import com.example.swp391_fall24_be.apis.bookings.DTOs.BookingDTO;
import com.example.swp391_fall24_be.apis.bookings.StatusEnum;
import com.example.swp391_fall24_be.entities.PrescriptionEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "transactions")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    private UUID id;
    private Account customerID;
    private List<Booking> bookingID;
    private List<PrescriptionEntity> prescriptionID;
    private PaymentMethodEnum paymentMethod;
    private LocalDateTime transactionDate;
    private StatusEnum status;
}
