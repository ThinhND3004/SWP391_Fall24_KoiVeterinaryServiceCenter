package com.example.swp391_fall24_be.apis.veterinarian;

import com.example.swp391_fall24_be.apis.accounts.Account;
import com.example.swp391_fall24_be.apis.bookings.Booking;
import com.example.swp391_fall24_be.apis.timetables.DTOs.TimetableDTO;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class VeterinarianService {
    private List<TimetableDTO> veterinarianTimetables; // Danh sách lịch trình của bác sĩ
    private List<Booking> bookings; // Danh sách các buổi hẹn đã đặt

    public List<Account> findAvailableVeterinarians(UUID serviceId, LocalDateTime requestedStartTime) {
        // Lấy ngày trong tuần và thời gian từ yêu cầu
        DayOfWeek requestedDay = requestedStartTime.getDayOfWeek();
        LocalTime requestedTime = requestedStartTime.toLocalTime();

        // Định nghĩa thời gian kết thúc dịch vụ dự kiến (+1 giờ sau thời gian bắt đầu)
        LocalTime estimatedEndTime = requestedTime.plusHours(1);

        // Bước 1: Tìm bác sĩ có sẵn vào ngày yêu cầu
        List<TimetableDTO> availableVeterinarians = veterinarianTimetables.stream()
                .filter(timetable -> timetable.getDayOfWeek().equals(requestedDay))
                .filter(timetable -> timetable.getStartTime().isBefore(requestedTime)
                        && timetable.getEndTime().isAfter(estimatedEndTime))
                .collect(Collectors.toList());

        // Bước 2: Lọc bác sĩ không bị đặt chỗ vào thời gian yêu cầu
        List<Account> availableVeterianAccounts = availableVeterinarians.stream()
                .filter(timetable -> isVeterinarianAvailable(timetable.getVeterinarianId(), requestedStartTime, estimatedEndTime))
                .map(timetable -> new Account(timetable.getVeterinarianId())) // Sử dụng ID để khởi tạo Account
                .collect(Collectors.toList());

        return availableVeterianAccounts;
    }

    // Phương thức trợ giúp để kiểm tra xem bác sĩ có khả dụng hay không (không có đặt chỗ xung đột)
    private boolean isVeterinarianAvailable(String veterinarianId, LocalDateTime requestedStartTime, LocalTime estimatedEndTime) {
        return bookings.stream()
                .filter(booking -> booking.getVeterinarianId().equals(veterinarianId))
                .noneMatch(booking -> {
                    // Lấy thời gian bắt đầu và kết thúc của đặt chỗ
                    LocalDateTime bookingStartTime = booking.getStartedAt();
                    LocalDateTime bookingEndTime = booking.getEndedAt();

                    // Kiểm tra xem có sự chồng chéo giữa thời gian yêu cầu và bất kỳ đặt chỗ nào đã có
                    return (requestedStartTime.isBefore(bookingEndTime) && estimatedEndTime.isAfter(bookingStartTime.toLocalTime()));
                });
    }
}