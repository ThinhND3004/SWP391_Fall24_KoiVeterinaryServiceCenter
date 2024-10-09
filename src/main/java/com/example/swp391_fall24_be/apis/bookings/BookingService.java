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
    protected void beforeCreate(BookingEntity bookingEntity) throws ProjectException {
        List<ErrorReport> errorReportList = new ArrayList<>();

        Optional<AccountEntity> customer = accountsRepository.findById(bookingEntity.getCustomer().getId());
        if (customer.isEmpty()) {
            errorReportList.add(new ErrorReport(
                    "AccountsService_beforeCreate",
                    ErrorEnum.EntityNotFound,
                    "Customer with ID " + bookingEntity.getCustomer().getId() + " does not exist."));
        } else {
            bookingEntity.setCustomer(customer.get());
        }

        Optional<AccountEntity> veterinarian = accountsRepository.findById(bookingEntity.getVeterinarianIsBooked().getId());
        if (veterinarian.isEmpty()) {
            errorReportList.add(new ErrorReport(
                    "AccountsService_beforeCreate",
                    ErrorEnum.EntityNotFound,
                    "Veterinarian with ID " + bookingEntity.getVeterinarianIsBooked().getId() + " does not exist."));
        } else {
            bookingEntity.setVeterinarianIsBooked(veterinarian.get());
        }

        Optional<ServiceEntity> service = servicesRepository.findById(bookingEntity.getService().getId());
        if (service.isEmpty()) {
            errorReportList.add(new ErrorReport(
                    "ServicesService_beforeCreate",
                    ErrorEnum.EntityNotFound,
                    "Service with ID " + bookingEntity.getService().getId() + " does not exist."));
        } else {
            bookingEntity.setService(service.get());
            bookingEntity.setTotalPrice(service.get().getPrice() + service.get().getTravelPricePerMeter() * bookingEntity.getDistanceKilometers());
        }

//        if (bookingEntity.getStartedAt() != null && (bookingEntity.getStartedAt().isBefore(LocalDateTime.now()) || bookingEntity.getStartedAt().isBefore(bookingEntity.getCreatedAt()))) {
//            errorReportList.add(new ErrorReport(
//                    "BookingService_beforeCreate",
//                    ErrorEnum.ValidationError,
//                    "The start time must be after the current time and after the creation time!"));
//        }

        if (!errorReportList.isEmpty()) {
            throw new ProjectException(errorReportList);
        }

    }

    @Override
    protected void beforeUpdate(BookingEntity oldEntity, BookingEntity newEntity) throws ProjectException {
        List<ErrorReport> errorReportList = new ArrayList<>();

        Optional<AccountEntity> veterinarianOpt = accountsRepository.findById(newEntity.getVeterinarianIsBooked().getId());
        if (veterinarianOpt.isEmpty()) {
            errorReportList.add(new ErrorReport(
                    "AccountsService_beforeUpdate",
                    ErrorEnum.EntityNotFound,
                    "Veterinarian with ID " + newEntity.getVeterinarianIsBooked().getId() + " does not exist."));
        } else {
            oldEntity.setVeterinarianIsBooked(veterinarianOpt.get());
        }

        Optional<ServiceEntity> serviceOpt = servicesRepository.findById(newEntity.getService().getId());
        if (serviceOpt.isEmpty()) {
            errorReportList.add(new ErrorReport(
                    "ServicesService_beforeUpdate",
                    ErrorEnum.EntityNotFound,
                    "Service with ID " + newEntity.getService().getId() + " does not exist."));
        } else {
            oldEntity.setService(serviceOpt.get());
            float totalPrice = serviceOpt.get().getPrice() +
                    serviceOpt.get().getTravelPricePerMeter() * newEntity.getDistanceKilometers();
            oldEntity.setTotalPrice(totalPrice);
        }

        if (newEntity.getStartedAt() != null &&
                (newEntity.getStartedAt().isBefore(LocalDateTime.now()) ||
                        newEntity.getStartedAt().isBefore(oldEntity.getStartedAt()))) {
            errorReportList.add(new ErrorReport(
                    "BookingService_beforeUpdate",
                    ErrorEnum.ValidationError,
                    "The start time must be after the current time and after the previous start time!"));
        }

        if (!errorReportList.isEmpty()) {
            throw new ProjectException(errorReportList);
        }
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

//    public BookingEntity createBooking(CreateBookingDTO createBookingDTO) {
//        // Lấy đối tượng AccountEntity của Customer từ database
//        AccountEntity customer = accountsRepository.findById(createBookingDTO.getCustomerId())
//                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
//
//        // Lấy đối tượng AccountEntity của Veterinarian từ database
//        AccountEntity veterinarian = accountsRepository.findById(createBookingDTO.getVeterianId())
//                .orElseThrow(() -> new IllegalArgumentException("Veterinarian not found"));
//
//        // Lấy đối tượng ServiceEntity từ database
//        ServiceEntity service = servicesRepository.findById(createBookingDTO.getServiceId())
//                .orElseThrow(() -> new IllegalArgumentException("Service not found"));
//
//        // Tạo đối tượng BookingEntity và set các giá trị
//        BookingEntity booking = new BookingEntity();
//        booking.setCustomer(customer);
//        booking.setVeterinarianIsBooked(veterinarian);
//        booking.setService(service);
//        booking.setAdditionalInformation(createBookingDTO.getDescription());
////        booking.setServicePrice(createBookingDTO.getServicePrice());
////        booking.setTravelPrice(createBookingDTO.getTravelPrice());
//        booking.setUserAddress(createBookingDTO.getUserAddress());
//        booking.setMeetingMethodEnum(createBookingDTO.getMeetingMethod());
//        booking.setStartedAt(createBookingDTO.getStartAt());
//        booking.setStatusEnum(StatusEnum.UNPAID);
//        booking.setDecline(false);
//
//        // Lưu đối tượng BookingEntity vào database
//        return bookingRepository.save(booking);
//    }

    public Optional<BookingEntity> getBookingById(String id) {
        return bookingRepository.findById(id);
    }
}
