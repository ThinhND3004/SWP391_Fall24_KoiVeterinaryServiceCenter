package com.example.swp391_fall24_be.apis.transactions;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity(name = "transaction_details")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionDetail {
    private UUID id;
    private Transaction transactionID;
    private TransactionStageEnum transactionStage;
    private float price;
}
