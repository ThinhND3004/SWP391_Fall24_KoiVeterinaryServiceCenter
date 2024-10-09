package com.example.swp391_fall24_be.apis.prescription.dtos;

import com.example.swp391_fall24_be.apis.prescription.PrescriptionEntity;
import com.example.swp391_fall24_be.apis.prescription.PrescriptionStatusEnum;
import com.example.swp391_fall24_be.core.IDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public class UpdatePrescriptionDto implements IDto<PrescriptionEntity> {

//    @NotNull(message = "Shipping is required!")
//    private ShippingEntity shippingEntity;

    @NotNull(message = "Amount is required!")
    @Positive(message = "Amount must be a positive number!")
    private Integer amount;

    @NotNull(message = "Total price is required!")
    @Positive(message = "Total price must be a positive number!")
    private Float totalPrice;

    @NotNull(message = "Status is required!")
    private PrescriptionStatusEnum prescriptionStatus;

    //    @NotNull(message = "Create time is required!")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Override
    public PrescriptionEntity toEntity() {
        PrescriptionEntity prescription = new PrescriptionEntity();
        prescription.setAmount(amount);
        prescription.setTotalPrice(totalPrice);
        prescription.setPrescriptionStatus(prescriptionStatus);
        prescription.setUpdatedAt(updatedAt);
        return prescription;
    }
}
