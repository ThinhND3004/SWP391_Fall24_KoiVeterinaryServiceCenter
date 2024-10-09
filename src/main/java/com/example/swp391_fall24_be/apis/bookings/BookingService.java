package com.example.swp391_fall24_be.apis.bookings;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.accounts.AccountsRepository;
import com.example.swp391_fall24_be.apis.bookings.DTOs.BookingDTO;
import com.example.swp391_fall24_be.apis.bookings.DTOs.CreateBookingDTO;
import com.example.swp391_fall24_be.apis.bookings.DTOs.PaginateBookingDTO;
import com.example.swp391_fall24_be.apis.bookings.DTOs.UpdateBookingDTO;
import com.example.swp391_fall24_be.apis.services.ServiceEntity;
import com.example.swp391_fall24_be.apis.services.ServicesRepository;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ErrorEnum;
import com.example.swp391_fall24_be.core.ErrorReport;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService extends AbstractService<BookingEntity, String, CreateBookingDTO, UpdateBookingDTO, PaginateBookingDTO> {
    @Override
    protected void beforeCreate(BookingEntity entity) throws ProjectException {
        List<ErrorReport> errorReportList = new ArrayList<>();

//        if(entity.getCreatedAt().isBefore(LocalDateTime.now())) {
//            errorReportList.add(new ErrorReport(
//                    "BookingService_beforeCreate",
//                    ErrorEnum.ValidationError,
//                    "Creation time cannot be in the past!"));
//        }

//        // Kiểm tra thời gian bắt đầu phải sau thời gian hiện tại và thời gian tạo
//        if (entity.getStartedAt() != null && (entity.getStartedAt().isBefore(LocalDateTime.now()) || entity.getStartedAt().isBefore(entity.getCreatedAt()))) {
//            errorReportList.add(new ErrorReport(
//                    "BookingService_beforeCreate",
//                    ErrorEnum.ValidationError,
//                    "The start time must be after the current time and after the creation time!"));
//        }
//
//        // Kiểm tra thời gian kết thúc phải sau thời gian bắt đầu
//        if (entity.getEndedAt() != null && entity.getStartedAt() != null && entity.getEndedAt().isBefore(entity.getStartedAt())) {
//            errorReportList.add(new ErrorReport(
//                    "BookingService_beforeCreate",
//                    ErrorEnum.ValidationError,
//                    "The end time must be after the start time!"));
//        }
    }

    @Override
    protected void beforeUpdate(BookingEntity oldEntity, BookingEntity newEntity) throws ProjectException {

    }

    @Override
    public BookingEntity delete(String id) throws ProjectException {
        return null;
    }

    @Autowired
    AccountsRepository accountsRepository;

    @Autowired
    ServicesRepository servicesRepository;

    @Autowired
    BookingRepository bookingRepository;

    public BookingEntity createBooking(CreateBookingDTO createBookingDTO) {
        // Lấy đối tượng AccountEntity của Customer từ database
        AccountEntity customer = accountsRepository.findById(createBookingDTO.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        // Lấy đối tượng AccountEntity của Veterinarian từ database
        AccountEntity veterinarian = accountsRepository.findById(createBookingDTO.getVeterianId())
                .orElseThrow(() -> new IllegalArgumentException("Veterinarian not found"));

        // Lấy đối tượng ServiceEntity từ database
        ServiceEntity service = servicesRepository.findById(createBookingDTO.getServiceId())
                .orElseThrow(() -> new IllegalArgumentException("Service not found"));

        // Tạo đối tượng BookingEntity và set các giá trị
        BookingEntity booking = new BookingEntity();
        booking.setCustomer(customer);
        booking.setVeterinarianIsBooked(veterinarian);
        booking.setService(service);
        booking.setDescription(createBookingDTO.getDescription());
        booking.setServicePrice(createBookingDTO.getServicePrice());
        booking.setTravelPrice(createBookingDTO.getTravelPrice());
        booking.setDestination(createBookingDTO.getDestination());
        booking.setMeetingMethodEnum(createBookingDTO.getMeetingMethod());
        booking.setStartedAt(createBookingDTO.getStartAt());
        booking.setStatusEnum(StatusEnum.UNPAID);
        booking.setDecline(false);

        // Lưu đối tượng BookingEntity vào database
        return bookingRepository.save(booking);
    }

    public Optional<BookingEntity> getBookingById(String id) {
        return bookingRepository.findById(id);
    }
}
