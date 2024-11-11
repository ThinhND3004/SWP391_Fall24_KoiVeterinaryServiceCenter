package com.example.swp391_fall24_be.apis.reports;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ReportsRepository extends JpaRepository<ReportEntity, String> {
    @Query("SELECT r FROM reports r WHERE r.booking.id = :bookingId")
    Optional<ReportEntity> findByBookingId(@Param("bookingId") String bookingId);
}
