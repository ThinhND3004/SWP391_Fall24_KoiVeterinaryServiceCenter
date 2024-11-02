package com.example.swp391_fall24_be.apis.bookings;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<BookingEntity, String> {
    List<BookingEntity> findByVeterianOrderByStartedAtAsc(AccountEntity veterian);

    List<BookingEntity> findByVeterianAndStatusEnumOrStatusEnumOrderByStartedAtAsc(AccountEntity veterian, StatusEnum statusEnum, StatusEnum statusEnum2);

    List<BookingEntity> findAllByStartedAtBetween(LocalDateTime startedAt, LocalDateTime endedAt);
}
