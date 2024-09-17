package com.example.swp391_fall24_be.apis.prescription.dtos;

import com.example.swp391_fall24_be.apis.prescription.PrescriptionEntity;
import com.example.swp391_fall24_be.core.IDto;
import com.example.swp391_fall24_be.entities.MedicineEntity;
import com.example.swp391_fall24_be.entities.ShippingEntity;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class UpdatePrescriptionDto implements IDto<PrescriptionEntity> {
    @NotBlank(message = "Shipping is required!")
    private ShippingEntity shippingEntity;
    @NotBlank(message = "Medicine is required!")
    private MedicineEntity medicineEntity;
    @NotBlank(message = "Total price is required!")
    private float totalPrice;
    @NotBlank(message = "Create time is required!")
    private LocalDateTime createAt;

    @Override
    public PrescriptionEntity toEntity() {
        PrescriptionEntity prescription = new PrescriptionEntity();
        prescription.setShippingID(shippingEntity);
        prescription.setMedicineID(medicineEntity);
        prescription.setTotalPrice(totalPrice);
        prescription.setCreatedAt(createAt);
        return prescription;
    }
}
