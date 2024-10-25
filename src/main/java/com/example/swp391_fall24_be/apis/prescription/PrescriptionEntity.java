package com.example.swp391_fall24_be.apis.prescription;

import com.example.swp391_fall24_be.apis.prescription.dtos.PrescriptionDto;
import com.example.swp391_fall24_be.apis.prescription_medicine.PrescriptionMedicine;
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
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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

    @ManyToOne
    @JoinColumn(name = "shipping_id")
    private ShippingEntity shippingID;

    @Column(name = "total_price",nullable = false, columnDefinition = "FLOAT")
    private Float totalPrice;

    @Column(name = "created_at", updatable = false, columnDefinition = "DATETIME")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PrescriptionStatusEnum status;

    @OneToMany(mappedBy = "prescription")
    private List<PrescriptionMedicine> prescriptionMedicines;

    @Override
    public PrescriptionDto toResponseDto() {
        PrescriptionDto prescriptionDto = new PrescriptionDto();
        prescriptionDto.setShippingEntity(shippingID);
        prescriptionDto.setTotalPrice(totalPrice);
        prescriptionDto.setCreatAt(createdAt);
        return prescriptionDto;
    }
}
