package com.example.swp391_fall24_be.apis.bookings;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.accounts.AccountRoleEnum;
import com.example.swp391_fall24_be.apis.accounts.AccountsRepository;
import com.example.swp391_fall24_be.apis.bookings.DTOs.CreateBookingDTO;
import com.example.swp391_fall24_be.apis.bookings.DTOs.PaginateBookingDTO;
import com.example.swp391_fall24_be.apis.bookings.DTOs.UpdateBookingDTO;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ErrorEnum;
import com.example.swp391_fall24_be.core.ErrorReport;
import com.example.swp391_fall24_be.core.ProjectException;
import com.example.swp391_fall24_be.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService extends AbstractService<BookingEntity, String, CreateBookingDTO, UpdateBookingDTO, PaginateBookingDTO> {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    AccountsRepository accountsRepository;

    @Override
    protected void beforeCreate(BookingEntity bookingEntity) throws ProjectException {
        List<ErrorReport> errorReportList = new ArrayList<>();

        bookingEntity.setCustomer(AuthUtils.getCurrentAccount());

        Optional<AccountEntity> findVeterianResult = accountsRepository.findById(bookingEntity.getVeterian().getId());
        if (findVeterianResult.isEmpty()) {
            errorReportList.add(new ErrorReport(
            "BookingsService_beforeCreate",
                    ErrorEnum.EntityNotFound,
                    "Veterinarian with ID " + bookingEntity.getVeterian().getId() + " does not exist."));
        } else if(findVeterianResult.get().getRole() != AccountRoleEnum.VETERIAN){
            errorReportList.add(new ErrorReport(
                    "BookingsService_beforeCreate",
                    ErrorEnum.EntityNotFound,
                    "Account with ID " + bookingEntity.getVeterian().getId() + " is not a veterian."));
        }

        if (!errorReportList.isEmpty()) {
            throw new ProjectException(errorReportList);
        }
    }

    @Override
    protected void beforeUpdate(BookingEntity oldEntity, BookingEntity newEntity) throws ProjectException {
        List<ErrorReport> errorReportList = new ArrayList<>();

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

}
