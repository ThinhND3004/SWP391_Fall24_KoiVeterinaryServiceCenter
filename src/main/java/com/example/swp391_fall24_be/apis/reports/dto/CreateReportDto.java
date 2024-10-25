package com.example.swp391_fall24_be.apis.reports.dto;

import com.example.swp391_fall24_be.apis.bookings.BookingEntity;
import com.example.swp391_fall24_be.apis.koispecies.KoiSpeciesEntity;
import com.example.swp391_fall24_be.apis.medicine.MedicineEntity;
import com.example.swp391_fall24_be.apis.ponds.dto.CreatePondDto;
import com.example.swp391_fall24_be.apis.prescription.PrescriptionEntity;
import com.example.swp391_fall24_be.apis.prescription.PrescriptionStatusEnum;
import com.example.swp391_fall24_be.apis.prescription_medicine.PrescriptionMedicine;
import com.example.swp391_fall24_be.core.IDto;
import com.example.swp391_fall24_be.apis.ponds.PondEntity;
import com.example.swp391_fall24_be.apis.reports.ReportEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class CreateReportDto implements IDto<ReportEntity> {
    @JsonProperty("bookingId")
    private String bookingId;

    @JsonProperty("koiSpeciesIdList")
    private List<String> koiSpeciesIdList;

    @JsonProperty("createPondDto")
    private CreatePondDto createPondDto;

    @JsonProperty("prescriptions")
    private List<ReportPrescriptionsDto> prescriptions;

    @NotBlank(message = "Diagnosis is required!")
    @JsonProperty("diagnosis")
    private String diagnosis;

    @JsonProperty("notes")
    private String notes;

    @Override
    public ReportEntity toEntity() {
        ReportEntity report = new ReportEntity();

        BookingEntity booking = new BookingEntity();
        booking.setId(bookingId);
        report.setBooking(booking);

        if(koiSpeciesIdList != null){
            List<KoiSpeciesEntity> koiSpecies = new ArrayList<>();
            for(String koiSpeciesID : koiSpeciesIdList) {
                KoiSpeciesEntity addKoiSpecies = new KoiSpeciesEntity();
                addKoiSpecies.setId(koiSpeciesID);
                koiSpecies.add(addKoiSpecies);
            }
            report.setKoiSpecies(koiSpecies);
        }

        if(createPondDto != null){
            report.setPond(createPondDto.toEntity());
        }

        if(prescriptions != null){
            PrescriptionEntity prescription = new PrescriptionEntity();
            List<PrescriptionMedicine> prescriptionMedicines = new ArrayList<>();
            for(ReportPrescriptionsDto dto : prescriptions){
                PrescriptionMedicine pm = new PrescriptionMedicine();

                MedicineEntity medicine = new MedicineEntity();
                medicine.setId(dto.getMedicineId());
                pm.setMedicine(medicine);
                pm.setAmount(dto.getAmount());

                prescriptionMedicines.add(pm);
            }

            prescription.setPrescriptionMedicines(prescriptionMedicines);
            prescription.setStatus(PrescriptionStatusEnum.PENDING);
            report.setPrescription(prescription);
        }

        report.setDiagnosis(diagnosis);
        report.setNotes(notes);
        return report;
    }
}
