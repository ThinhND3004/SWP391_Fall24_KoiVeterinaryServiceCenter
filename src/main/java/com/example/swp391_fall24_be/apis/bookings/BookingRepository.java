package com.example.swp391_fall24_be.apis.bookings;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
//    Optional<Booking> findById(UUID id);
}
