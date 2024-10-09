package com.example.swp391_fall24_be.apis.bookings;

import com.example.swp391_fall24_be.apis.bookings.DTOs.BookingDTO;
import com.example.swp391_fall24_be.apis.bookings.DTOs.CreateBookingDTO;
import com.example.swp391_fall24_be.apis.bookings.DTOs.PaginateBookingDTO;
import com.example.swp391_fall24_be.apis.bookings.DTOs.UpdateBookingDTO;
import com.example.swp391_fall24_be.core.AbstractController;
import com.example.swp391_fall24_be.core.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
@Tag(name = "Bookings", description = "Booking APIs")
public class BookingController extends AbstractController<BookingEntity, String, CreateBookingDTO, UpdateBookingDTO, PaginateBookingDTO, BookingDTO> {
    @Autowired
    private BookingService bookingService;

    @Override
    public ResponseDto<List<BookingDTO>> doGetMany(PaginateBookingDTO paginateKoiSpeciesDto) {
        return super.doGetMany(paginateKoiSpeciesDto);
    }

    @Override
    public ResponseDto<BookingDTO> doGet(String id) {
        return super.doGet(id);
    }

    @Override
    public ResponseDto<BookingDTO> doPost(CreateBookingDTO dto) {
        return super.doPost(dto);
    }

    @Override
    public ResponseDto<BookingDTO> doPut(String id, UpdateBookingDTO dto) {
        return super.doPut(id, dto);
    }

    @Override
    public ResponseDto<BookingDTO> doDelete(String id) {
        return super.doDelete(id);
    }

//    @PostMapping
//    @Operation(summary = "Create a new booking")
//    public ResponseEntity<BookingEntity> createBooking(@RequestBody CreateBookingDTO createBookingDTO) {
//        BookingEntity booking = bookingService.createBooking(createBookingDTO);
//        return new ResponseEntity<>(booking, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{id}")
//    @Operation(summary = "Get a booking by ID")
//    public ResponseEntity<BookingDTO> getBookingById(@PathVariable("id") String id) {
//        Optional<BookingEntity> bookingEntityOpt = bookingService.getBookingById(id);
//        return bookingEntityOpt.map(entity -> {
//            BookingDTO bookingDTO = entity.toResponseDto(); // Gọi phương thức chuyển đổi
//            return ResponseEntity.ok(bookingDTO); // Trả về BookingDTO
//        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Nếu không tìm thấy, trả về 404
//    }
}
