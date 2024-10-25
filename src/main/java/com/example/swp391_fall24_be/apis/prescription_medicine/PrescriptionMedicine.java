package com.example.swp391_fall24_be.apis.prescription_medicine;

import com.example.swp391_fall24_be.apis.medicine.MedicineEntity;
import com.example.swp391_fall24_be.apis.prescription.PrescriptionEntity;
import com.example.swp391_fall24_be.apis.prescription_medicine.dtos.PrescriptionMedicineDto;
import com.example.swp391_fall24_be.core.IObject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "prescription_medicine")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class PrescriptionMedicine implements IObject<PrescriptionMedicineDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "prescription_id", nullable = false)
    @ManyToOne
    private PrescriptionEntity prescription;

    @JoinColumn(name = "medicine_id", nullable = false)
    @ManyToOne
    private MedicineEntity medicine;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Override
    public PrescriptionMedicineDto toResponseDto() {
        PrescriptionMedicineDto dto = new PrescriptionMedicineDto();
        dto.setAmount(amount);
        return dto;
    }
}
