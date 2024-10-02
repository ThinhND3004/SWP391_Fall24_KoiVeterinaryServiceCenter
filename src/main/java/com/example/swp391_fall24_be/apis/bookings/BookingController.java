package com.example.swp391_fall24_be.apis.bookings;

import com.example.swp391_fall24_be.apis.bookings.DTOs.BookingDTO;
import com.example.swp391_fall24_be.apis.bookings.DTOs.CreateBookingDTO;
import com.example.swp391_fall24_be.apis.bookings.DTOs.PaginateBookingDTO;
import com.example.swp391_fall24_be.apis.bookings.DTOs.UpdateBookingDTO;
import com.example.swp391_fall24_be.core.AbstractController;
import com.example.swp391_fall24_be.core.ResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bookings")
@Tag(name = "Bookings", description = "Booking APIs")
public class BookingController extends AbstractController<Booking, String, CreateBookingDTO, UpdateBookingDTO, PaginateBookingDTO, BookingDTO> {
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
}
