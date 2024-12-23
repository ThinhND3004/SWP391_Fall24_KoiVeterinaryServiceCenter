package com.example.swp391_fall24_be.apis;

import com.example.swp391_fall24_be.apis.bookings.BookingRepository;
import com.example.swp391_fall24_be.apis.notifications.NotificationsRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CleanUpService {

    private final BookingRepository bookingRepository;
    private final NotificationsRepository notificationsRepository;

    public CleanUpService(BookingRepository bookingRepository, NotificationsRepository notificationsRepository) {
        this.bookingRepository = bookingRepository;
        this.notificationsRepository = notificationsRepository;
    }

    @Scheduled(cron = "0 0 * * * ?") // Runs every hour
    public void cancelOutdatedBooking() {
        LocalDateTime expiryDate = LocalDateTime.now();
        bookingRepository.updateOutdatedBooking(expiryDate); // Update booking status PENDING t CANCELED when expired
        notificationsRepository.deleteExpiredNotifications(expiryDate.minusHours(3)); // DELETE notifications AFTER created for 3 hours
    }

    @Scheduled(cron = "0 * * * * ?")
    public void deleteUnpaidBooking() {
        LocalDateTime timeLimit = LocalDateTime.now().minusMinutes(30);
        bookingRepository.deleteUnpaidBooking(timeLimit); // Update booking status PENDING t CANCELED when expired
    }
}
