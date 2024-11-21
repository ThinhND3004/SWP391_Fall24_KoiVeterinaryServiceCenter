package com.example.swp391_fall24_be.apis.bookings;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, String> {
    @Query("SELECT b FROM bookings b WHERE b.veterian = :veterian AND b.statusEnum IN :statuses")
    List<BookingEntity> findByVeterianAndStatuses(@Param("veterian") AccountEntity veterian, @Param("statuses") List<StatusEnum> statuses);

    List<BookingEntity> findAllByStartedAtBetween(LocalDateTime startedAt, LocalDateTime endedAt);

    @Modifying
    @Transactional
    @Query("UPDATE bookings b SET b.statusEnum = 'CANCELED' WHERE b.startedAt < :expiryDate AND b.statusEnum = 'PENDING'")
    void updateOutdatedBooking(@Param("expiryDate") LocalDateTime expiryDate);

    @Modifying
    @Transactional
    @Query("DELETE FROM bookings b WHERE b.statusEnum = 'UNPAID' AND createdAt <= :timeLimit")
    void deleteUnpaidBooking(@Param("timeLimit") LocalDateTime timeLimit);
}
