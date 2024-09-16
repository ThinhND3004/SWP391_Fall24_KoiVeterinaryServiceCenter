package com.example.swp391_fall24_be.entities;

import com.example.swp391_fall24_be.apis.treatments.dto.TreatmentDto;
import com.example.swp391_fall24_be.core.IObject;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "treatments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NotBlank
public class TreatmentEntity implements IObject<TreatmentDto> {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "fish_id")
    private KoiFishEntity fishID;

    @ManyToOne
    @JoinColumn(name = "pond_id")
    private PondEntity pondID;

    @Column(name = "prescription_id")
    private UUID prescriptionID;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "notes")
    private String notes;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Override
    public TreatmentDto toResponseDto() {
        TreatmentDto treatmentDto = new TreatmentDto();
        treatmentDto.setFishID(fishID);
        treatmentDto.setPondID(pondID);
        treatmentDto.setPrescriptionID(prescriptionID);
        treatmentDto.setDiagnosis(diagnosis);
        treatmentDto.setNotes(notes);
        treatmentDto.setCreatedAt(createdAt);
        return treatmentDto;
    }
}
