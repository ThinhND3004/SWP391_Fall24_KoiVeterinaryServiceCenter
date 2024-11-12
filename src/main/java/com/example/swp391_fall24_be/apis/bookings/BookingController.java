package com.example.swp391_fall24_be.apis.bookings;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.bookings.DTOs.BookingDTO;
import com.example.swp391_fall24_be.apis.bookings.DTOs.CreateBookingDTO;
import com.example.swp391_fall24_be.apis.bookings.DTOs.PaginateBookingDTO;
import com.example.swp391_fall24_be.apis.bookings.DTOs.UpdateBookingDTO;
import com.example.swp391_fall24_be.core.AbstractController;
import com.example.swp391_fall24_be.core.ResponseDto;
import com.example.swp391_fall24_be.utils.AuthUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bookings")
@Tag(name = "Bookings", description = "Booking APIs")
public class BookingController extends AbstractController<BookingEntity, String, CreateBookingDTO, UpdateBookingDTO, PaginateBookingDTO, BookingDTO> {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/by-veterian")
    public ResponseDto<List<BookingDTO>> getByVeterian(
            @Valid PaginateBookingDTO paginateBookingDTO
    ){
        try {
            AccountEntity veterian = AuthUtils.getCurrentAccount();
            return new ResponseDto<>(
                    HttpStatus.OK.value(),
                    "Get booking for veterian successful!",
                    bookingService.getByVeterian(veterian, paginateBookingDTO.toEntity().getStatusEnum()),
                    null
            );
        }
        catch (Exception e){
            List<String> errorList = new ArrayList<>();
            errorList.add(e.getMessage());
            return new ResponseDto<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "Get booking for veterian fail!",
                    null,
                    errorList
            );
        }
    }

    @PostMapping("/assign-veterian/{bookingId}/{veterianEmail}")
    public ResponseDto<BookingDTO> assignVeterian(
            @PathVariable("bookingId") String bookingId,
            @PathVariable("veterianEmail") String veterianEmail
    ){
        try {
            BookingEntity assignedBooking = bookingService.assignVeterian(bookingId,veterianEmail);
            return new ResponseDto<>(
                HttpStatus.OK.value(),
                "Assign veterian successful!",
                assignedBooking.toResponseDto(),
                null
            );
        }
         catch (Exception e){
            List<String> errorList = new ArrayList<>();
            errorList.add(e.getMessage());
            return new ResponseDto<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "Cannot assign veterian to booking!",
                    null,
                    errorList
            );
        }
    }
}
