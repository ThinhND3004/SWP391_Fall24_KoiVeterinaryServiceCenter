package com.example.swp391_fall24_be.apis.reports;

import com.example.swp391_fall24_be.apis.bookings.BookingEntity;
import com.example.swp391_fall24_be.apis.koispecies.KoiSpeciesEntity;
import com.example.swp391_fall24_be.apis.ponds.PondEntity;
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
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "reports")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class ReportEntity implements IObject<ReportDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "report_id"))
    private List<KoiSpeciesEntity> koiSpecies;

    @OneToOne
    @JoinColumn(name = "pond_id")
    private PondEntity pond;

    @JoinColumn(name = "prescription_id")
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
    private BookingEntity booking;

    @Override
    public ReportDto toResponseDto() {
        ReportDto treatmentDto = new ReportDto();
        treatmentDto.setKoiSpecies(koiSpecies);
//        treatmentDto.setPrescription(prescription);
        treatmentDto.setPond(pond);

        treatmentDto.setDiagnosis(diagnosis);
        treatmentDto.setNotes(notes);
        return treatmentDto;
    }
}
