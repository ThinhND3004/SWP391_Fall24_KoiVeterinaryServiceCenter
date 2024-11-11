package com.example.swp391_fall24_be.apis.notifications;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.bookings.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NotificationsRepository extends JpaRepository<NotificationEntity,Long> {
    List<NotificationEntity> findAllByAccount(AccountEntity account);
    void deleteAllByBooking(BookingEntity booking);
    @Query("SELECT n FROM notifications as n WHERE n.account.email = ?1 AND n.booking.id = ?2")
    Optional<NotificationEntity> findByAccountEmailAndBookingId(String accountEmail, String bookingId);
}
