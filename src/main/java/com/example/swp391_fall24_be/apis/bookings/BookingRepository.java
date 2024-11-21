package com.example.swp391_fall24_be.apis.bookings;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<BookingEntity, String> {
    List<BookingEntity> findByVeterianAndStatusEnum(AccountEntity veterian, StatusEnum statusEnum2);
    List<BookingEntity> findByVeterianAndStatusEnumOrStatusEnum(AccountEntity veterian, StatusEnum statusEnum, StatusEnum statusEnum2);
    List<BookingEntity> findAllByStartedAtBetween(LocalDateTime startedAt, LocalDateTime endedAt);

    @Modifying
    @Transactional
    @Query("UPDATE bookings b SET b.statusEnum = 'CANCELED' WHERE b.startedAt < :expiryDate AND b.statusEnum = 'PENDING'")
    void updateOutdatedBooking(@Param("expiryDate") LocalDateTime expiryDate);
}
