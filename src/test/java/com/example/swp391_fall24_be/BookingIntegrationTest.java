package com.example.swp391_fall24_be;

import com.example.swp391_fall24_be.apis.auth.dto.AccountLoginDto;
import com.example.swp391_fall24_be.apis.bookings.BookingRepository;
import com.example.swp391_fall24_be.apis.bookings.DTOs.CreateBookingDTO;
import com.example.swp391_fall24_be.apis.bookings.MeetingMethodEnum;
import com.example.swp391_fall24_be.apis.vnpay.PaymentForEnum;
import com.example.swp391_fall24_be.apis.vnpay.dtos.CreatePaymentDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private BookingRepository bookingRepository;

	@BeforeEach
	public void setup() {
		// Xóa dữ liệu cũ trong repository trước khi test
		bookingRepository.deleteAll();
	}

	@Test
	public void loginWithValidCredentials_ShouldReturnOkAndToken() throws Exception {
		AccountLoginDto loginRequest = new AccountLoginDto("nguyenchit088@gmail.com", "12345678");
		String loginJson = objectMapper.writeValueAsString(loginRequest);

		mockMvc.perform(post("http://localhost:8080/auth/login-password")
						.contentType(MediaType.APPLICATION_JSON)
						.content(loginJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value(200))  // Kiểm tra status = 200
				.andExpect(jsonPath("$.message").value("Login successfully!"))  // Kiểm tra thông báo thành công
				.andExpect(jsonPath("$.data.token").exists()) // Kiểm tra token tồn tại trong dữ liệu trả về
				.andExpect(jsonPath("$.err").isEmpty()); // Kiểm tra err là null hoặc rỗng
	}

	@Test
	public void loginWithInvalidCredentials_ShouldReturnUnauthorized() throws Exception {
		AccountLoginDto loginRequest = new AccountLoginDto("nguyenchit088@gmail.com", "123");
		String loginJson = objectMapper.writeValueAsString(loginRequest);

		mockMvc.perform(post("http://localhost:8080/auth/login-password")
						.contentType(MediaType.APPLICATION_JSON)
						.content(loginJson))// Xác nhận trạng thái phản hồi là 400 Bad Request
				.andExpect(jsonPath("$.status").value(400))  // Xác nhận trường "status" trong JSON là 400
				.andExpect(jsonPath("$.message").value("Cannot login!")) // Xác nhận thông báo lỗi là "Cannot login!"
				.andExpect(jsonPath("$.data").isEmpty()) // Xác nhận trường "data" là null
				.andExpect(jsonPath("$.err[0]").value("Password is incorrect")); // Xác nhận lỗi đầu tiên trong "err" là "Password is incorrect"
	}

	@Test
	public void createBooking_ShouldReturnBookingEntity() throws Exception {
		CreateBookingDTO createBookingDTO = new CreateBookingDTO();
		createBookingDTO.setServiceId("74e0b2d4-7886-4502-bced-af3e1b21fafe");
		createBookingDTO.setVeterianEmail("vet1@gmail.com");
		createBookingDTO.setAdditionalInformation("Hẻm 90 An Nhơn, Ho Chi Minh City, Ho Chi Minh, Vietnam");
		createBookingDTO.setDistanceMeters(0f);
		createBookingDTO.setMeetingMethod(MeetingMethodEnum.ONLINE);
		//createBookingDTO.setServicePrice(500000f);
		createBookingDTO.setStartAt(LocalDateTime.parse("2024-11-10T02:00:00"));
		//createBookingDTO.setTravelPrice(0f);
		createBookingDTO.setUserAddress("");

		String bookingJson = objectMapper.writeValueAsString(createBookingDTO);

		mockMvc.perform(post("/bookings")
						.header("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI0ZGE4N2QyMi02NGExLTQyNGQtYjkyYS1kNWEwNWU0NjNiODYiLCJpYXQiOjE3MzA5NjIxNTksImV4cCI6MTgxNzM2MjE1OX0.1oGUjDAL2NndwI3TCrWYqSog8WEiu7IgH6LFKdcG7ss")
						.contentType(MediaType.APPLICATION_JSON)
						.content(bookingJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.serviceName").value("Online Consultant"))
				.andExpect(jsonPath("$.data.meetingMethod").value("ONLINE"))
				.andExpect(jsonPath("$.data.veterinarianFullName").value("Do Khang"))
				.andExpect(jsonPath("$.data.startedAt").value("2024-11-10T02:00:00"))
				.andExpect(jsonPath("$.data.statusEnum").value("CONFIRMED"));
	}

	@Test
	public void createBooking_ShouldReturnBadRequest_WhenStartAtIsInThePast() throws Exception {
		// Mock input data for the booking creation with 'startAt' in the past
		CreateBookingDTO createBookingDTO = new CreateBookingDTO();

		createBookingDTO.setVeterianEmail("vet1@gmail.com");
		createBookingDTO.setServiceId("74e0b2d4-7886-4502-bced-af3e1b21fafe");
		createBookingDTO.setAdditionalInformation("Hello");
		//createBookingDTO.setServicePrice(500000f);
		//createBookingDTO.setTravelPrice(2000f);
		createBookingDTO.setDistanceMeters(10f);
		createBookingDTO.setUserAddress("HCM");
		createBookingDTO.setMeetingMethod(MeetingMethodEnum.ONLINE);
		createBookingDTO.setStartAt(LocalDateTime.parse("2020-11-05T06:51:39.126"));  // Start date is in the past

		// Convert the booking DTO to JSON
		String bookingJson = objectMapper.writeValueAsString(createBookingDTO);

		// Perform the POST request to create a booking
		mockMvc.perform(post("/bookings")
						.header("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI0ZGE4N2QyMi02NGExLTQyNGQtYjkyYS1kNWEwNWU0NjNiODYiLCJpYXQiOjE3MzA5NjIxNTksImV4cCI6MTgxNzM2MjE1OX0.1oGUjDAL2NndwI3TCrWYqSog8WEiu7IgH6LFKdcG7ss")  // Assuming a valid token for authorization
						.contentType(MediaType.APPLICATION_JSON)
						.content(bookingJson))
				.andExpect(jsonPath("$.status").value(400))  // Check the 'status' is 400
				.andExpect(jsonPath("$.message").value("Violate validation constraint"))  // Check the 'message'
				.andExpect(jsonPath("$.data").doesNotExist())  // Ensure the 'data' field is null
				.andExpect(jsonPath("$.err[0]").value("Start At must be in the future or present"));  // Ensure the error message is correct
	}


	@Test
	public void createVNPayPayment_ShouldReturnPaymentUrl() throws Exception {
		// Mock input data for the payment creation
		CreatePaymentDto createPaymentDTO = new CreatePaymentDto();

		createPaymentDTO.setPayment(PaymentForEnum.BOOKING);
		createPaymentDTO.setTotalPrice(10000000f); // Total price for the booking payment

		// Convert the payment DTO to JSON
		String paymentJson = objectMapper.writeValueAsString(createPaymentDTO);

		// Perform the POST request to create a payment
		mockMvc.perform(post("/vnpay/create-payment")
						.header("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI0ZGE4N2QyMi02NGExLTQyNGQtYjkyYS1kNWEwNWU0NjNiODYiLCJpYXQiOjE3MzA5NjIxNTksImV4cCI6MTgxNzM2MjE1OX0.1oGUjDAL2NndwI3TCrWYqSog8WEiu7IgH6LFKdcG7ss")  // Assuming a valid token for authorization
						.contentType(MediaType.APPLICATION_JSON)
						.content(paymentJson))
				.andExpect(status().isOk())  // Check if the response is OK (200)
				.andExpect(jsonPath("$.data").isNotEmpty());  // Ensure that the 'data' field is not empty
	}

}

