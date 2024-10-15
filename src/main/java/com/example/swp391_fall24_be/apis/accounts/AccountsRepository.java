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
}
