package com.example.swp391_fall24_be.apis.treatments;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TreatmentsRepository extends JpaRepository<Treatment, UUID> {

}
