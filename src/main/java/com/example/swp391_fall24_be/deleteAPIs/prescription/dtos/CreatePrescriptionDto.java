package com.example.swp391_fall24_be.deleteAPIs.prescription.dtos;

import com.example.swp391_fall24_be.deleteAPIs.prescription.PrescriptionEntity;
import com.example.swp391_fall24_be.core.IDto;
import com.example.swp391_fall24_be.deleteAPIs.medicine.MedicineEntity;
import com.example.swp391_fall24_be.deleteAPIs.shipping.ShippingEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class CreatePrescriptionDto implements IDto<PrescriptionEntity> {

    @NotNull(message = "Shipping is required!")
    private ShippingEntity shippingEntity;

    @NotNull(message = "Medicine is required!")
    private MedicineEntity medicineEntity;

    @NotNull(message = "Total price is required!")
    @Positive(message = "Total price must be a positive number!")
    private Float totalPrice;

    @NotNull(message = "Create time is required!")
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
