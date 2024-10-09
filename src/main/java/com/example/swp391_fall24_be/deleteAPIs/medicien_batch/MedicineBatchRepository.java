package com.example.swp391_fall24_be.deleteAPIs.medicien_batch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineBatchRepository extends JpaRepository<MedicineBatchEntity, String> {
}
