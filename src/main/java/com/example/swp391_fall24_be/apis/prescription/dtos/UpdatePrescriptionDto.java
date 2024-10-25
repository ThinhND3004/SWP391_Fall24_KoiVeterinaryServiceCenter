package com.example.swp391_fall24_be.apis.prescription.dtos;

import com.example.swp391_fall24_be.apis.prescription.PrescriptionEntity;
import com.example.swp391_fall24_be.core.IDto;
import com.example.swp391_fall24_be.apis.medicine.MedicineEntity;
import com.example.swp391_fall24_be.apis.shipping.ShippingEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class UpdatePrescriptionDto implements IDto<PrescriptionEntity> {

    @NotNull(message = "Shipping is required!")
    private ShippingEntity shippingEntity;

    @NotNull(message = "Medicine is required!")
    private MedicineEntity medicineEntity;

    @NotNull(message = "Total price is required!")
    @Positive(message = "Total price must be a positive number!")
    private float totalPrice;

    @NotNull(message = "Create time is required!")
    private LocalDateTime createAt;

    @Override
    public PrescriptionEntity toEntity() {
        PrescriptionEntity prescription = new PrescriptionEntity();
        if (shippingEntity != null) {
            prescription.setShippingID(shippingEntity);
        }

        prescription.setTotalPrice(totalPrice);
        prescription.setCreatedAt(createAt);
        return prescription;
    }
}
