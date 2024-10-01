package com.example.swp391_fall24_be.apis.bookings;

import com.example.swp391_fall24_be.apis.bookings.DTOs.CreateBookingDTO;
import com.example.swp391_fall24_be.apis.bookings.DTOs.PaginateBookingDTO;
import com.example.swp391_fall24_be.apis.bookings.DTOs.UpdateBookingDTO;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ErrorEnum;
import com.example.swp391_fall24_be.core.ErrorReport;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService extends AbstractService<Booking, String, CreateBookingDTO, UpdateBookingDTO, PaginateBookingDTO> {
    @Override
    protected void beforeCreate(Booking entity) throws ProjectException {
        List<ErrorReport> errorReportList = new ArrayList<>();

        if(entity.getCreatedAt().isBefore(LocalDateTime.now())) {
            errorReportList.add(new ErrorReport(
                    "BookingService_beforeCreate",
                    ErrorEnum.ValidationError,
                    "Creation time cannot be in the past!"));
        }
        // Kiểm tra thời gian cập nhật (nếu có) phải sau thời gian tạo
        if (entity.getUpdatedAt() != null && entity.getUpdatedAt().isBefore(entity.getCreatedAt())) {
            errorReportList.add(new ErrorReport(
                    "BookingService_beforeCreate",
                    ErrorEnum.ValidationError,
                    "Update time cannot be before creation time!"));
        }

        // Kiểm tra thời gian bắt đầu phải sau thời gian hiện tại và thời gian tạo
        if (entity.getStartedAt() != null && (entity.getStartedAt().isBefore(LocalDateTime.now()) || entity.getStartedAt().isBefore(entity.getCreatedAt()))) {
            errorReportList.add(new ErrorReport(
                    "BookingService_beforeCreate",
                    ErrorEnum.ValidationError,
                    "The start time must be after the current time and after the creation time!"));
        }

        // Kiểm tra thời gian kết thúc phải sau thời gian bắt đầu
        if (entity.getEndedAt() != null && entity.getStartedAt() != null && entity.getEndedAt().isBefore(entity.getStartedAt())) {
            errorReportList.add(new ErrorReport(
                    "BookingService_beforeCreate",
                    ErrorEnum.ValidationError,
                    "The end time must be after the start time!"));
        }
    }

    @Override
    protected void beforeUpdate(Booking oldEntity, Booking newEntity) throws ProjectException {

    }

    @Override
    public Booking delete(String id) throws ProjectException {
        return null;
    }
}
