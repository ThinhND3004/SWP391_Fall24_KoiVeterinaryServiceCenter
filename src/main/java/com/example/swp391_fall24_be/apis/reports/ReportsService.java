package com.example.swp391_fall24_be.apis.reports;

import com.example.swp391_fall24_be.apis.bookings.BookingEntity;
import com.example.swp391_fall24_be.apis.bookings.BookingRepository;
import com.example.swp391_fall24_be.apis.bookings.StatusEnum;
import com.example.swp391_fall24_be.apis.medicine.MedicineEntity;
import com.example.swp391_fall24_be.apis.medicine.MedicineRepository;
import com.example.swp391_fall24_be.apis.notifications.NotificationsRepository;
import com.example.swp391_fall24_be.apis.ponds.PondEntity;
import com.example.swp391_fall24_be.apis.ponds.PondsRepository;
import com.example.swp391_fall24_be.apis.prescription.PrescriptionEntity;
import com.example.swp391_fall24_be.apis.prescription.PrescriptionRepository;
import com.example.swp391_fall24_be.apis.prescription_medicine.PrescriptionMedicine;
import com.example.swp391_fall24_be.apis.prescription_medicine.PrescriptionMedicineRepository;
import com.example.swp391_fall24_be.apis.reports.dtos.CreateReportDto;
import com.example.swp391_fall24_be.apis.reports.dtos.PaginateReportDto;
import com.example.swp391_fall24_be.apis.reports.dtos.ReportDto;
import com.example.swp391_fall24_be.apis.reports.dtos.UpdateReportDto;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReportsService extends AbstractService<ReportEntity, String, CreateReportDto, UpdateReportDto, PaginateReportDto> {
    private final ReportsRepository reportsRepository;
    private final PondsRepository pondsRepository;
    private final PrescriptionMedicineRepository pmRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final MedicineRepository medicineRepository;
    private final BookingRepository bookingRepository;
    private final NotificationsRepository notificationsRepository;

    public ReportsService(ReportsRepository treatmentsRepository, PondsRepository pondsRepository, PrescriptionMedicineRepository pmRepository, PrescriptionRepository prescriptionRepository, MedicineRepository medicineRepository, BookingRepository bookingRepository,
                          NotificationsRepository notificationsRepository) {
        this.reportsRepository = treatmentsRepository;
        this.pondsRepository = pondsRepository;
        this.pmRepository = pmRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.medicineRepository = medicineRepository;
        this.bookingRepository = bookingRepository;
        this.notificationsRepository = notificationsRepository;
    }

    @Override
    @Transactional
    public void beforeCreate(ReportEntity entity) throws ProjectException {
    }

    @Override
    @Transactional
    public ReportEntity create(CreateReportDto dto) throws ProjectException {
        ReportEntity entity = dto.toEntity();

        // Save Pond if present
        if (entity.getPond() != null) {
            PondEntity pondEntity = pondsRepository.save(entity.getPond());
            entity.setPond(pondEntity);
        }

        // Process Prescription and Medicines if present
        PrescriptionEntity prescription = entity.getPrescription();
        if (prescription != null) {
            List<PrescriptionMedicine> prescriptionMedicines = prescription.getPrescriptionMedicines();
            prescription.setPrescriptionMedicines(null); // Temporarily detach to save Prescription first
            prescription.setTotalPrice(0f);  // Initialize total price

            // Save Prescription and calculate total price
            PrescriptionEntity savedPrescription = prescriptionRepository.save(prescription);
            entity.setPrescription(savedPrescription);

            // Batch process medicines to calculate total price
            if (prescriptionMedicines != null && !prescriptionMedicines.isEmpty()) {
                float totalPrice = 0;

                for (PrescriptionMedicine pm : prescriptionMedicines) {
                    MedicineEntity medicine = pm.getMedicine();
                    if (medicine != null) {
                        totalPrice += medicine.getPrice(); // Calculate total price
                    }
                    pm.setPrescription(savedPrescription); // Assign saved prescription to medicine
                }

                pmRepository.saveAll(prescriptionMedicines); // Batch save all PrescriptionMedicine
                savedPrescription.setTotalPrice(totalPrice); // Set total price
                prescriptionRepository.save(savedPrescription); // Update Prescription with total price
            }
        }

        // Save ReportEntity
        entity = reportsRepository.save(entity);

        // Update Booking status if present
        BookingEntity booking = entity.getBooking();
        if (booking != null && booking.getStatusEnum() != StatusEnum.COMPLETED) {
            Optional<BookingEntity> optionalBooking = bookingRepository.findById(booking.getId());
            if (optionalBooking.isPresent()) {
                booking = optionalBooking.get();
                booking.setStatusEnum(StatusEnum.COMPLETED);
                booking.setEndedAt(LocalDateTime.now());
                bookingRepository.save(booking);

                // Delete notifications related to the booking
                notificationsRepository.deleteAllByBooking(booking);
            }
        }

        return entity;
    }



    @Override
    protected void beforeUpdate(ReportEntity oldEntity, ReportEntity newEntity) throws ProjectException {

    }

    @Override
    public ReportEntity delete(String id) throws ProjectException {
        return null;
    }

    public ReportDto getByBookingId(String bookingId) throws Exception {
        Optional<ReportEntity> findResult = reportsRepository.findByBookingId(bookingId);
        if (findResult.isEmpty()) {
            throw new Exception("Cannot find reports with this bookingId "+bookingId);
        }
        return findResult.get().toResponseDto();
    }
}
