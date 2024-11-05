package com.example.swp391_fall24_be.apis.dashboard;

import com.example.swp391_fall24_be.apis.bookings.BookingEntity;
import com.example.swp391_fall24_be.apis.bookings.BookingRepository;
import com.example.swp391_fall24_be.apis.feedbacks.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

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
        Map<String, Map<String, Object>> chosenServices = new HashMap<>();
        Map<Double, Integer> serviceRating = new HashMap<>();
        Map<String, Integer> startTimeDaily = new HashMap<>();
        Map<String, Integer> startTimeWeekly = new HashMap<>();

        bookingsIn12Months.forEach(booking -> {
            LocalDateTime createdAt = booking.getCreatedAt();
            if (createdAt != null) {
                String month = createdAt.getMonth().toString();
                double price = booking.getService().getPrice();
                revenuePerMonth.put(month, revenuePerMonth.getOrDefault(month, 0d) + price);
            }
        });

        bookings.forEach(booking -> {
            LocalDateTime startedAt = booking.getStartedAt();

            String serviceId = booking.getService().getId();
            chosenServices.putIfAbsent(serviceId, new HashMap<>());
            chosenServices.get(serviceId).put("name", booking.getService().getName());
            chosenServices.get(serviceId).put("count", (int) chosenServices.get(serviceId).getOrDefault("count", 0) + 1);

            if (startedAt != null) {
                String dayOfWeek = startedAt.getDayOfWeek().toString();
                String time = startedAt.getHour() + "_" + startedAt.getMinute();
                startTimeDaily.put(time, startTimeDaily.getOrDefault(time, 0) + 1);
                startTimeWeekly.put(dayOfWeek, startTimeWeekly.getOrDefault(dayOfWeek, 0) + 1);
            }

            Feedback feedback = booking.getFeedbackId();
            if (feedback != null) {
                double rating = feedback.getStarRating();
                serviceRating.put(rating, serviceRating.getOrDefault(rating, 0) + 1);
            }
        });

        dashboardData.getData().put("revenue", revenuePerMonth.entrySet().stream()
                .sorted(Map.Entry.comparingByKey((month1, month2) -> {
                    List<String> months = List.of("JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER");
                    return Integer.compare(months.indexOf(month1), months.indexOf(month2));
                }))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                )));
        dashboardData.getData().put("services", chosenServices);
        dashboardData.getData().put("rating", serviceRating);
        dashboardData.getData().put("startTimeDaily", startTimeDaily.entrySet().stream()
                .sorted(Map.Entry.comparingByKey((time1, time2) -> {
                    String[] parts1 = time1.split("_");
                    String[] parts2 = time2.split("_");
                    int hour1 = Integer.parseInt(parts1[0]);
                    int minute1 = Integer.parseInt(parts1[1]);
                    int hour2 = Integer.parseInt(parts2[0]);
                    int minute2 = Integer.parseInt(parts2[1]);
                    if (hour1 != hour2) {
                        return Integer.compare(hour1, hour2);
                    } else {
                        return Integer.compare(minute1, minute2);
                    }
                }))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                )));
        dashboardData.getData().put("startTimeWeekly", startTimeWeekly.entrySet().stream()
                .sorted(Map.Entry.comparingByKey((day1, day2) -> {
                    List<String> daysOfWeek = List.of("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY");
                    return Integer.compare(daysOfWeek.indexOf(day1), daysOfWeek.indexOf(day2));
                }))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                )));
        return dashboardData;
    }

    public DashboardData getVeterinarianDashboardData() {
        DashboardData dashboardData = new DashboardData();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = now.withMonth(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        List<BookingEntity> bookingsInMonth = bookingRepository.findAllByStartedAtBetween(start, now);
        List<BookingEntity> bookings = bookingRepository.findAll();

        AtomicLong totalWorkTime = new AtomicLong();
        Map<String, Map<String, Object>> averageWorkTime = new HashMap<>();
        Map<String, Map<String, Object>> totalWorkTimeInMonth = new HashMap<>();

        bookings.forEach(booking -> {
            LocalDateTime startedAt = booking.getStartedAt();
            LocalDateTime endedAt = booking.getEndedAt();
            if (startedAt != null && endedAt != null) {
                long duration = Duration.between(startedAt, endedAt).toHours();
                totalWorkTime.addAndGet(duration);
            }
        });

        bookingsInMonth.forEach(booking -> {
            LocalDateTime startedAt = booking.getStartedAt();
            LocalDateTime endedAt = booking.getEndedAt();
            if (startedAt != null && endedAt != null) {
                String veterianId = booking.getVeterian().getId();
                long duration = Duration.between(startedAt, endedAt).toHours();

                totalWorkTimeInMonth.putIfAbsent(veterianId, new HashMap<>());
                totalWorkTimeInMonth.get(veterianId).put("name", booking.getService().getName());
                totalWorkTimeInMonth.get(veterianId).put("count", (long) totalWorkTimeInMonth.get(veterianId).getOrDefault("count", 0L) + duration);

                double average = (double) totalWorkTimeInMonth.get(veterianId).get("count") / totalWorkTimeInMonth.size();
                averageWorkTime.put(veterianId, new HashMap<>());
                averageWorkTime.get(veterianId).put("name", booking.getService().getName());
                averageWorkTime.get(veterianId).put("average", average);
            }
        });

        dashboardData.getData().put("totalWorkTime", totalWorkTime);
        dashboardData.getData().put("averageWorkTime", averageWorkTime);
        dashboardData.getData().put("totalWorkTimePerMonth", totalWorkTimeInMonth);

        return dashboardData;
    }
}
