package com.example.swp391_fall24_be.apis.reports;

import com.example.swp391_fall24_be.apis.bookings.BookingEntity;
import com.example.swp391_fall24_be.apis.images.ImageEntity;
import com.example.swp391_fall24_be.apis.koispecies.KoiSpeciesEntity;
import com.example.swp391_fall24_be.apis.ponds.PondEntity;
import com.example.swp391_fall24_be.apis.prescription.PrescriptionEntity;
import com.example.swp391_fall24_be.apis.reports.dto.ReportDto;
import com.example.swp391_fall24_be.core.IObject;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity(name = "reports")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NotBlank
public class ReportEntity implements IObject<ReportDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "diagnosis", columnDefinition = "VARCHAR(100)")
    private String diagnosis;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "created_at", columnDefinition = "DATETIME")
    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "pond_id", nullable = false)
    private PondEntity pond;

    @ManyToOne
    @JoinColumn(name = "koi_species_id", nullable = false)
    private KoiSpeciesEntity koiSpecies;

    @JoinColumn(name = "prescription_id", nullable = false)
    @OneToOne
    private PrescriptionEntity prescription;

    @OneToOne(mappedBy = "report")
//    @JsonManagedReference
    private BookingEntity booking;

//    @ManyToOne
//    @JoinColumn(name = "picture_id")
//    private ImageEntity picture;

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
