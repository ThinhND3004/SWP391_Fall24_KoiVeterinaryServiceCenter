package com.example.swp391_fall24_be.apis.reports;

import com.example.swp391_fall24_be.apis.bookings.BookingEntity;
import com.example.swp391_fall24_be.apis.bookings.BookingRepository;
import com.example.swp391_fall24_be.apis.bookings.StatusEnum;
import com.example.swp391_fall24_be.apis.medicine.MedicineEntity;
import com.example.swp391_fall24_be.apis.medicine.MedicineRepository;
import com.example.swp391_fall24_be.apis.ponds.PondEntity;
import com.example.swp391_fall24_be.apis.ponds.PondsRepository;
import com.example.swp391_fall24_be.apis.prescription.PrescriptionEntity;
import com.example.swp391_fall24_be.apis.prescription.PrescriptionRepository;
import com.example.swp391_fall24_be.apis.prescription_medicine.PrescriptionMedicine;
import com.example.swp391_fall24_be.apis.prescription_medicine.PrescriptionMedicineRepository;
import com.example.swp391_fall24_be.apis.reports.dto.CreateReportDto;
import com.example.swp391_fall24_be.apis.reports.dto.PaginateReportDto;
import com.example.swp391_fall24_be.apis.reports.dto.UpdateReportDto;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReportsService extends AbstractService<ReportEntity, String, CreateReportDto, UpdateReportDto, PaginateReportDto> {
    private final ReportsRepository reportsRepository;
    private final PondsRepository pondsRepository;
    private final PrescriptionMedicineRepository pmRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final MedicineRepository medicineRepository;
    private final BookingRepository bookingRepository;

    public ReportsService(ReportsRepository treatmentsRepository, PondsRepository pondsRepository, PrescriptionMedicineRepository pmRepository, PrescriptionRepository prescriptionRepository, MedicineRepository medicineRepository, BookingRepository bookingRepository) {
        this.reportsRepository = treatmentsRepository;
        this.pondsRepository = pondsRepository;
        this.pmRepository = pmRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.medicineRepository = medicineRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    @Transactional
    public void beforeCreate(ReportEntity entity) throws ProjectException {
    }

    @Override
    @Transactional
    public ReportEntity create(CreateReportDto dto) throws ProjectException {
        ReportEntity entity = dto.toEntity();
        // Save Pond
        if(entity.getPond() != null){
            PondEntity pondEntity = pondsRepository.save(entity.getPond());
            entity.setPond(pondEntity);
        }


        if(entity.getPrescription() != null){
            PrescriptionEntity prescription = entity.getPrescription();
            float totalPrice = 0;
            for(PrescriptionMedicine pm : prescription.getPrescriptionMedicines()){
                Optional<MedicineEntity> findMedicine = medicineRepository.findById(pm.getMedicine().getId());
                if(findMedicine.isPresent()){
                    totalPrice += findMedicine.get().getPrice();
                }
            }
            prescription.setTotalPrice(totalPrice);


            PrescriptionEntity savedPrescription = prescriptionRepository.save(prescription);
            entity.setPrescription(savedPrescription);
            // Save prescription medicines
            for(PrescriptionMedicine pm : savedPrescription.getPrescriptionMedicines()){
                pm.setPrescription(savedPrescription);
                pmRepository.save(pm);
            }
        }

        entity = reportsRepository.save(entity);

        // Set status for booking
        Optional<BookingEntity> findBooking = bookingRepository.findById(entity.getBooking().getId());
        if(findBooking.isPresent()){
            BookingEntity booking = findBooking.get();
            booking.setStatusEnum(StatusEnum.COMPLETED);
            bookingRepository.save(booking);
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
}
