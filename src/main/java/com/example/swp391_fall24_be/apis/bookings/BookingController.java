package com.example.swp391_fall24_be.apis.bookings;

import com.example.swp391_fall24_be.annotations.CurrentAccount;
import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.bookings.DTOs.BookingDTO;
import com.example.swp391_fall24_be.apis.bookings.DTOs.CreateBookingDTO;
import com.example.swp391_fall24_be.apis.bookings.DTOs.PaginateBookingDTO;
import com.example.swp391_fall24_be.apis.bookings.DTOs.UpdateBookingDTO;
import com.example.swp391_fall24_be.core.AbstractController;
import com.example.swp391_fall24_be.core.ResponseDto;
import com.example.swp391_fall24_be.utils.AuthUtils;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
@Tag(name = "Bookings", description = "Booking APIs")
@CrossOrigin(origins = "http://localhost:5173") // Set the allowed origin
public class BookingController extends AbstractController<BookingEntity, String, CreateBookingDTO, UpdateBookingDTO, PaginateBookingDTO, BookingDTO> {
}
