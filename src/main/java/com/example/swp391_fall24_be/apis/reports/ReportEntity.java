package com.example.swp391_fall24_be.apis.reports;

import com.example.swp391_fall24_be.apis.bookings.BookingEntity;
import com.example.swp391_fall24_be.apis.koispecies.KoiSpeciesEntity;
import com.example.swp391_fall24_be.apis.koispecies.dto.KoiSpeciesDto;
import com.example.swp391_fall24_be.apis.ponds.PondEntity;
import com.example.swp391_fall24_be.apis.prescription.PrescriptionEntity;
import com.example.swp391_fall24_be.apis.reports.dtos.ReportDto;
import com.example.swp391_fall24_be.core.IObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(joinColumns = @JoinColumn(name = "report_id"))
    private List<KoiSpeciesEntity> koiSpecies;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pond_id")
    private PondEntity pond;

    @JoinColumn(name = "prescription_id")
    @OneToOne()
    private PrescriptionEntity prescription;

    @Column(name = "diagnosis", columnDefinition = "VARCHAR(100)")
    private String diagnosis;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "created_at", columnDefinition = "DATETIME")
    @CreatedDate
    private LocalDateTime createdAt;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id",unique = true)
    private BookingEntity booking;

    @Override
    public ReportDto toResponseDto() {
        ReportDto treatmentDto = new ReportDto();
        if(pond != null) treatmentDto.setPondDto(pond.toResponseDto());
        if(prescription != null) treatmentDto.setPrescriptionDto(prescription.toResponseDto());

        if(koiSpecies != null ){
            List<KoiSpeciesDto> koiSpeciesDtos = new ArrayList<>();
            for(KoiSpeciesEntity kSpecies : koiSpecies){
                koiSpeciesDtos.add(kSpecies.toResponseDto());
            }
            treatmentDto.setKoiSpeciesDtoList(koiSpeciesDtos);
        }
        
        treatmentDto.setDiagnosis(diagnosis);
        treatmentDto.setNotes(notes);
        treatmentDto.setCreatedAt(createdAt);
        return treatmentDto;
    }
}
