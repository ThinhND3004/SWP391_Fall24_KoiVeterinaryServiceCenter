package com.example.swp391_fall24_be.apis.bookings;

import com.example.swp391_fall24_be.apis.bookings.DTOs.BookingDTO;
import com.example.swp391_fall24_be.apis.bookings.DTOs.CreateBookingDTO;
import com.example.swp391_fall24_be.apis.bookings.DTOs.PaginateBookingDTO;
import com.example.swp391_fall24_be.apis.bookings.DTOs.UpdateBookingDTO;
import com.example.swp391_fall24_be.core.AbstractController;
import com.example.swp391_fall24_be.core.ResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bookings")
@Tag(name = "Bookings", description = "Booking APIs")
@CrossOrigin(origins = "http://localhost:5173") // Set the allowed origin
public class BookingController extends AbstractController<BookingEntity, String, CreateBookingDTO, UpdateBookingDTO, PaginateBookingDTO, BookingDTO> {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
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
