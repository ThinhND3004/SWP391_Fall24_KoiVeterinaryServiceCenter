package com.example.swp391_fall24_be.apis.reports;

import com.example.swp391_fall24_be.apis.bookings.Booking;
import com.example.swp391_fall24_be.apis.koispecies.KoiSpeciesEntity;
import com.example.swp391_fall24_be.apis.prescription.PrescriptionEntity;
import com.example.swp391_fall24_be.apis.reports.dto.ReportDto;
import com.example.swp391_fall24_be.core.IObject;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity(name = "treatments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NotBlank
public class ReportEntity implements IObject<ReportDto> {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "koi_species_id", nullable = false)
    private KoiSpeciesEntity koiSpecies;

    @JoinColumn(name = "prescription_id", nullable = false)
    @OneToOne
    private PrescriptionEntity prescription;

    @Column(name = "diagnosis", columnDefinition = "VARCHAR(100)")
    private String diagnosis;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "created_at", columnDefinition = "DATETIME")
    @CreatedDate
    private LocalDateTime createdAt;

    @OneToOne
    private Booking booking;

    @Override
    public ReportDto toResponseDto() {
        ReportDto treatmentDto = new ReportDto();
        treatmentDto.setKoiSpecies(koiSpecies);
        treatmentDto.setPrescription(prescription);
        treatmentDto.setDiagnosis(diagnosis);
        treatmentDto.setNotes(notes);
        return treatmentDto;
    }
}
