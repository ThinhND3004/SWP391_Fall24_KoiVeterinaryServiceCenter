package com.example.swp391_fall24_be.apis.notifications;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.bookings.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface NotificationsRepository extends JpaRepository<NotificationEntity,Long> {
    List<NotificationEntity> findAllByAccount(AccountEntity account);

    void deleteAllByBooking(BookingEntity booking);

    @Query("SELECT COUNT(n) > 0 FROM notifications AS n WHERE n.account.email = ?1 AND n.booking.id = ?2")
    boolean existsByAccountEmailAndBookingId(String accountEmail, String bookingId);

    @Modifying
    @Query("DELETE FROM notifications n WHERE n.createdAt < :expiredAt")
    void deleteExpiredNotifications(LocalDateTime expiredAt);
}
