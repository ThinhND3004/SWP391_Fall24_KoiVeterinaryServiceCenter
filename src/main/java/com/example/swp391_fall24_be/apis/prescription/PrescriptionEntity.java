package com.example.swp391_fall24_be.apis.prescription;

import com.example.swp391_fall24_be.apis.prescription.dtos.PrescriptionDto;
import com.example.swp391_fall24_be.apis.reports.ReportEntity;
import com.example.swp391_fall24_be.core.IObject;
import com.example.swp391_fall24_be.apis.medicine.MedicineEntity;
import com.example.swp391_fall24_be.apis.shipping.ShippingEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "prescriptions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NotBlank
public class PrescriptionEntity implements IObject<PrescriptionDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "medicine_id", nullable = false)
    private MedicineEntity medicineID;

    @ManyToOne
    @JoinColumn(name = "shipping_id", nullable = false)
    private ShippingEntity shippingID;

    @Column(name = "total_price",nullable = false)
    private float totalPrice;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PrescriptionStatusEnum status;

    @ManyToMany(mappedBy = "prescriptionEntities")
    private Set<MedicineEntity> medicineEntities;

    @OneToOne
    private ReportEntity report;

    @Override
    public PrescriptionDto toResponseDto() {
        PrescriptionDto prescriptionDto = new PrescriptionDto();
        prescriptionDto.setShippingEntity(shippingID);
        prescriptionDto.setMedicineEntity(medicineID);
        prescriptionDto.setTotalPrice(totalPrice);
        prescriptionDto.setCreatAt(createdAt);
        return prescriptionDto;
    }
}
