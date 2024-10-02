package com.example.swp391_fall24_be.apis.transactions;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "transaction_details")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id; // Using UUID for unique identifier

    @JoinColumn(name = "transaction_id")
    @OneToOne // Change to ManyToOne if a transaction can have multiple details
    private Transaction transaction;

    @Enumerated(EnumType.STRING)
    private TransactionStageEnum transactionStage;

    @Column(nullable = false)
    private BigDecimal price; // Changed to BigDecimal for precision
}
