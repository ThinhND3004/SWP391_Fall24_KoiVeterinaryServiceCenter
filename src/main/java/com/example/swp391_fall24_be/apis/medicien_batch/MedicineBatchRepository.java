package com.example.swp391_fall24_be.apis.medicien_batch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface MedicineBatchRepository extends JpaRepository<MedicineBatchEntity, String> {
}
