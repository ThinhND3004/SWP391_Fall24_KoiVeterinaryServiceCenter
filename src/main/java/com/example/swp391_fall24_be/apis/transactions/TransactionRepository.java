package com.example.swp391_fall24_be.apis.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {
//    List<Transaction> findByVeterinarianIdAndTimeBetween(String id, LocalDateTime startTime, LocalDateTime endTime);
}
