package com.example.swp391_fall24_be.apis.notifications;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.bookings.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationsRepository extends JpaRepository<NotificationEntity,Long> {
    List<NotificationEntity> findAllByAccount(AccountEntity account);
    void deleteAllByBooking(BookingEntity booking);
}
