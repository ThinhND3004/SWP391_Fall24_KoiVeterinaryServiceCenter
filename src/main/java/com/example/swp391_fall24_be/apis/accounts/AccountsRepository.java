package com.example.swp391_fall24_be.apis.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AccountsRepository extends JpaRepository<AccountEntity, String> {
    Optional<AccountEntity> findByEmail(String email);

    Optional<AccountEntity> findByPhone(String phone);

    List<AccountEntity> findAllByRoleAndIsDisable(AccountRoleEnum role, boolean disable);

    @Query(value = "SELECT a.* FROM accounts a " +
            "JOIN profile p ON a.profile_id = p.id " +
            "JOIN timetables t ON p.id = t.profile_id " +
            "JOIN bookings b ON a.id = b.account_id " +
            "JOIN service s ON b.service_id = s.id " +
            "WHERE :searchTime > t.start_time AND :searchTime < t.end_time " +
            "AND :searchTime > DATEADD(HOUR, s.estimated_time + 1, b.started_at) " +
            "AND :searchTime < DATEADD(HOUR, 1, b.started_at)",
            nativeQuery = true)
    List<AccountEntity> findIdleVeterianBySearchTime(@Param("searchTime") LocalDateTime searchTime);
}
