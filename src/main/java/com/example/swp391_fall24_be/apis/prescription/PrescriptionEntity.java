package com.example.swp391_fall24_be.apis.prescription;

import com.example.swp391_fall24_be.apis.medicine.MedicineEntity;
import com.example.swp391_fall24_be.apis.prescription.dtos.PrescriptionDto;
import com.example.swp391_fall24_be.apis.prescription_medicine.PrescriptionMedicine;
import com.example.swp391_fall24_be.apis.reports.ReportEntity;
import com.example.swp391_fall24_be.apis.transactions.TransactionEntity;
import com.example.swp391_fall24_be.core.IObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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
public class PrescriptionEntity implements IObject<PrescriptionDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "id", nullable = false, columnDefinition = "VARCHAR(40)")
    private String id;

    @ManyToOne
    @JoinColumn(name = "medicine", nullable = false)
    private MedicineEntity medicine;

    @Column(name = "amount", columnDefinition = "INT")
    private Integer amount;

    @Column(name = "total_price",nullable = false, columnDefinition = "FLOAT")
    private Float totalPrice;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PrescriptionStatusEnum prescriptionStatus;

    @Column(name = "created_at", updatable = false, columnDefinition = "DATETIME")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at", updatable = true, columnDefinition = "DATETIME")
    @CreatedDate
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "prescription")
    private Set<PrescriptionMedicine> prescriptionMedicines;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "report_id", nullable = true)  // prescription quản lý quan hệ với report
    private ReportEntity report;

    @OneToOne(mappedBy = "prescription", cascade = CascadeType.ALL)
    private TransactionEntity transaction;

    @Override
    public PrescriptionDto toResponseDto() {
        PrescriptionDto prescriptionDto = new PrescriptionDto();
//        prescriptionDto.setShippingEntity(shippingID);
//        prescriptionDto.setMedicineEntity(medicine);
        prescriptionDto.setAmount(amount);
        prescriptionDto.setTotalPrice(totalPrice);
        prescriptionDto.setPrescriptionStatus(prescriptionStatus);
        prescriptionDto.setCreatedAt(createdAt);
        prescriptionDto.setUpdatedAt(updatedAt);
//        prescriptionDto.setCreatAt(createdAt);
        return prescriptionDto;
    }
}
