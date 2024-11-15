package com.example.swp391_fall24_be.apis.prescription;

import com.example.swp391_fall24_be.apis.prescription.dtos.PrescriptionDto;
import com.example.swp391_fall24_be.apis.prescription_medicine.PrescriptionMedicine;
import com.example.swp391_fall24_be.apis.prescription_medicine.dtos.PrescriptionMedicineDto;
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

@Entity(name = "prescriptions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class PrescriptionEntity implements IObject<PrescriptionDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    @Column(name = "total_price",nullable = false, columnDefinition = "FLOAT")
    private Float totalPrice;

    @Column(name = "created_at", updatable = false, columnDefinition = "DATETIME")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PrescriptionStatusEnum status;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrescriptionMedicine> prescriptionMedicines;

    @Override
    public PrescriptionDto toResponseDto() {
        PrescriptionDto prescriptionDto = new PrescriptionDto();

        if(prescriptionMedicines != null){
            List<PrescriptionMedicineDto> prescriptionMedicineDtos = new ArrayList<>();
            for(PrescriptionMedicine prescriptionMedicine : prescriptionMedicines){
                prescriptionMedicineDtos.add(prescriptionMedicine.toResponseDto());
            }
            prescriptionDto.setPrescriptionMedicineDto(prescriptionMedicineDtos);
        }

        prescriptionDto.setTotalPrice(totalPrice);
        prescriptionDto.setCreatAt(createdAt);
        return prescriptionDto;
    }
}
