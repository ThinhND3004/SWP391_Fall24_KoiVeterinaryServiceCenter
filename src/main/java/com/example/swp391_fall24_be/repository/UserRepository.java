package com.example.swp391_fall24_be.repository;

import com.example.swp391_fall24_be.apis.accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByEmail(String email);
}
