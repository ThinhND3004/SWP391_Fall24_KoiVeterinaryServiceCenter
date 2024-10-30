package com.example.swp391_fall24_be.apis.accounts;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountsRepository extends JpaRepository<AccountEntity, String> {
    Optional<AccountEntity> findByEmail(String email);

    Optional<AccountEntity> findByPhone(String phone);

    @Modifying
    @Query("UPDATE accounts a SET a.isDisable = :disable WHERE a.email = :email")
    int updateAccountStatus(
            @Param("email") String email,
            @Param("disable") boolean disable
    );

    @Query("SELECT e FROM accounts e " +
            "WHERE " +
            "   LOWER(CONCAT(e.firstName, ' ', e.lastName)) LIKE LOWER(CONCAT('%', :searchName, '%')) " +
            "   AND e.role = :role")
    List<AccountEntity> findBySearchFullName(
            @Param("searchName") String searchName,
            @Param("role") AccountRoleEnum role,
            Pageable pageable
    );

    List<AccountEntity> findAllByRoleAndIsDisable(AccountRoleEnum role, boolean disable);


}
