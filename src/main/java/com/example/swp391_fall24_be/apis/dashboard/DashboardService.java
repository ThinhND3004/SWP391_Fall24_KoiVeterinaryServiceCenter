package com.example.swp391_fall24_be.apis.dashboard;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.accounts.AccountRoleEnum;
import com.example.swp391_fall24_be.apis.accounts.AccountsRepository;
import com.example.swp391_fall24_be.apis.bookings.BookingEntity;
import com.example.swp391_fall24_be.apis.bookings.BookingRepository;
import com.example.swp391_fall24_be.apis.feedbacks.Feedback;
import com.example.swp391_fall24_be.apis.feedbacks.FeedbackRepository;
import com.example.swp391_fall24_be.apis.prescription.PrescriptionEntity;
import com.example.swp391_fall24_be.apis.prescription.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    AccountsRepository accountsRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    PrescriptionRepository prescriptionRepository;

    public DashboardData getBookingDashboardData() {
        DashboardData dashboardData = new DashboardData();

        // Example data fetching and processing
        List<AccountEntity> accounts = accountsRepository.findAllByRoleAndIsDisable(AccountRoleEnum.CUSTOMER, false);
        List<BookingEntity> bookings = bookingRepository.findAll();
        List<Feedback> feedbacks = feedbackRepository.findAll();
        // Populate the dashboard data


        //total sales
        //visitors
        //total bookings
        //refunded bookings

        //total working hours
        //average working hours
        //salary

        double totalSales = bookings.stream().mapToDouble(BookingEntity::getServicePrice).sum();
        int visitors = accounts.size();
        int totalBookings = bookings.size();
        int refundedBookings = (int) bookings.stream().filter(BookingEntity::getIsDecline).count();


        dashboardData.getData().put("totalSales", totalSales);
        dashboardData.getData().put("visitors",visitors);
        dashboardData.getData().put("totalBookings", totalBookings);
        dashboardData.getData().put("refundedBookings", refundedBookings);

        //revenue (per month)
        //service rating (per service)
        //prescription revenue (per month)
        Map<String, Double> revenuePerMonth = new HashMap<>();
        bookings.forEach(booking -> {
            String month = booking.getCreatedAt().getMonth().toString();
            double revenue = booking.getServicePrice();
            if (revenuePerMonth.containsKey(month)) {
                revenuePerMonth.put(month, revenuePerMonth.get(month) + revenue);
            } else {
                revenuePerMonth.put(month, revenue);
            }
        });
        dashboardData.getData().put("revenues", revenuePerMonth);

        Map<String, Double> serviceRating = new HashMap<>();
        feedbacks.forEach(feedback -> {
            String service = feedback.getBooking().getService().getName();
            double rating = feedback.getStarRating();
            if (serviceRating.containsKey(service)) {
                serviceRating.put(service, (serviceRating.get(service) + rating) / 2);
            } else {
                serviceRating.put(service, rating);
            }
        });
        dashboardData.getData().put("serviceRating", serviceRating);

        Map<String, Double> prescriptionRevenuePerMonth = new HashMap<>();
        List<PrescriptionEntity> prescriptions = prescriptionRepository.findAll();
        prescriptions.forEach(prescription -> {
            String month = prescription.getCreatedAt().getMonth().toString();
            double revenue = prescription.getPrescriptionMedicines().stream().mapToDouble(medicine -> medicine.getMedicine().getPrice() * medicine.getAmount()).sum();
            if (prescriptionRevenuePerMonth.containsKey(month)) {
                prescriptionRevenuePerMonth.put(month, prescriptionRevenuePerMonth.get(month) + revenue);
            } else {
                prescriptionRevenuePerMonth.put(month, revenue);
            }
        });
        return dashboardData;
    }

    public DashboardData getVeterinarianDashboardData() {
        DashboardData dashboardData = new DashboardData();

        List<AccountEntity> accounts = accountsRepository.findAllByRoleAndIsDisable(AccountRoleEnum.CUSTOMER, false);
        List<BookingEntity> bookings = bookingRepository.findAll();

        int totalWorkingHours = bookings.stream().mapToInt(booking -> booking.getEndedAt().getHour() - booking.getStartedAt().getHour()).sum();
        int averageWorkingHours = totalWorkingHours / accounts.size();
        int salary = totalWorkingHours * 20; // Assuming hourly rate is 20 units

        dashboardData.getData().put("totalWorkingHours", totalWorkingHours);
        dashboardData.getData().put("averageWorkingHours", averageWorkingHours);
        dashboardData.getData().put("salary", salary);
        return dashboardData;
    }
}
