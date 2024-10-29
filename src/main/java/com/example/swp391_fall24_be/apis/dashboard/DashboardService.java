package com.example.swp391_fall24_be.apis.dashboard;

import com.example.swp391_fall24_be.apis.bookings.BookingEntity;
import com.example.swp391_fall24_be.apis.bookings.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DashboardService {
    @Autowired
    BookingRepository bookingRepository;

    public DashboardData getBookingDashboardData() {
        DashboardData dashboardData = new DashboardData();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = now.minusMonths(12);
        List<BookingEntity> bookingsIn12Months = bookingRepository.findAllByStartedAtBetween(start, now);
        List<BookingEntity> bookings = bookingRepository.findAll();
        Map<String, Double> revenuePerMonth = new HashMap<>();
        Map<String, Integer> chosenServices = new HashMap<>();
        Map<String, Integer> startTime = new HashMap<>();

        bookingsIn12Months.forEach(booking -> {
            String month = booking.getStartedAt().getMonth().toString();
            double price = booking.getService().getPrice();
            revenuePerMonth.put(month, revenuePerMonth.get(month) + price);
        });

        bookings.forEach(booking -> {
            String serviceId = booking.getService().getId();
            chosenServices.put(serviceId, chosenServices.get(serviceId) + 1);

            //day of week and time
            String time = booking.getStartedAt().getDayOfWeek().toString() + "_" +
                    booking.getStartedAt().getHour() + "_" +
                    booking.getStartedAt().getMinute();
            startTime.put(time, startTime.get(time) + 1);
        });

        dashboardData.getData().put("revenue", monthlyData(revenuePerMonth));
        dashboardData.getData().put("services", chosenServices);
        dashboardData.getData().put("startTime", startTime);
        return dashboardData;
    }

    public DashboardData getVeterinarianDashboardData() {
        DashboardData dashboardData = new DashboardData();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = now.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        List<BookingEntity> bookingsInMonth = bookingRepository.findAllByStartedAtBetween(start, now);
        List<BookingEntity> bookings = bookingRepository.findAll();

        AtomicLong totalWorkTime = new AtomicLong();
        Map<String, Double> averageWorkTime = new HashMap<>();
        Map<String, Long> totalWorkTimeInMonth = new HashMap<>();

        bookings.forEach(booking -> {
            LocalDateTime startedAt = booking.getStartedAt();
            LocalDateTime endedAt = booking.getEndedAt();
            long duration = Duration.between(startedAt, endedAt).toHours();

            totalWorkTime.addAndGet(duration);
        });

        bookingsInMonth.forEach(booking -> {
            String veterianId = booking.getVeterian().getId();

            LocalDateTime startedAt = booking.getStartedAt();
            LocalDateTime endedAt = booking.getEndedAt();
            Long duration = Duration.between(startedAt, endedAt).toHours();

            totalWorkTimeInMonth.put(veterianId, totalWorkTimeInMonth.get(veterianId) + duration);
            double average = (double) totalWorkTimeInMonth.get(veterianId) / totalWorkTimeInMonth.size();
            averageWorkTime.put(veterianId, average);
        });

        dashboardData.getData().put("totalWorkTime", totalWorkTime);
        dashboardData.getData().put("averageWorkTime", averageWorkTime);
        dashboardData.getData().put("totalWorkTimePerMonth", totalWorkTimeInMonth);

        return dashboardData;
    }

    private List<Map<String, Object>> monthlyData(Map<String, ?> dataPerMonth) {
        List<Map<String, Object>> formattedData = new ArrayList<>();
        Map<String, Map<String, Object>> monthlyData = new HashMap<>();

        dataPerMonth.forEach((key, value) -> {
            String[] parts = key.split("_");
            String month = parts[0];
            String type = parts[1];

            monthlyData.putIfAbsent(month, new HashMap<>());
            monthlyData.get(month).put(type, value);
        });

        monthlyData.forEach((month, data) -> {
            Map<String, Object> monthData = new HashMap<>();
            monthData.put("month", month);
            monthData.put("data", data);
            formattedData.add(monthData);
        });

        return formattedData;
    }
}
