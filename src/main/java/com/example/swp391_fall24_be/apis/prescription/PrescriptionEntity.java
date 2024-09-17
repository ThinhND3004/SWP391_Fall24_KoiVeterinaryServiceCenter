package com.example.swp391_fall24_be.apis.prescription;

import com.example.swp391_fall24_be.apis.prescription.dtos.PrescriptionDto;
import com.example.swp391_fall24_be.core.IDto;
import com.example.swp391_fall24_be.core.IObject;
import com.example.swp391_fall24_be.entities.MedicineEntity;
import com.example.swp391_fall24_be.entities.ShippingEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "prescriptions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NotBlank
public class PrescriptionEntity implements IObject<PrescriptionDto> {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private MedicineEntity medicineID;

    @ManyToOne
    @JoinColumn(name = "shipping_id")
    private ShippingEntity shippingID;

    @Column(name = "total_price")
    private float totalPrice;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

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
