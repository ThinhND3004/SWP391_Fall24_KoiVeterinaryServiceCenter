package com.example.swp391_fall24_be.apis.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Account, String> {
    Optional<Account> findByEmail(String email);
    Optional<Account> findByPhone(String phone);
}
