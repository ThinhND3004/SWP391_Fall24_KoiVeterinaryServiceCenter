package com.example.swp391_fall24_be.apis.prescription.dtos;

import com.example.swp391_fall24_be.apis.prescription.PrescriptionEntity;
import com.example.swp391_fall24_be.apis.prescription.PrescriptionStatusEnum;
import com.example.swp391_fall24_be.core.IDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public class CreatePrescriptionDto implements IDto<PrescriptionEntity> {

//    @NotNull(message = "Medicine is required!")
//    private MedicineEntity medicineEntity;

    @NotNull(message = "Amount is required!")
    @Positive(message = "Amount must be a positive number!")
    private Integer amount;

    @NotNull(message = "Total price is required!")
    @Positive(message = "Total price must be a positive number!")
    private Float totalPrice;

    @NotNull(message = "Status is required!")
    private PrescriptionStatusEnum prescriptionStatus;

//    @NotNull(message = "Create time is required!")
    @CreatedDate
    private LocalDateTime createAt;

    @Override
    public PrescriptionEntity toEntity() {
        PrescriptionEntity prescription = new PrescriptionEntity();
//        prescription.setShippingID(shippingEntity);
//        prescription.setMedicine(medicineEntity);
        prescription.setAmount(amount);
        prescription.setTotalPrice(totalPrice);
        prescription.setPrescriptionStatus(prescriptionStatus);
        prescription.setCreatedAt(createAt);
        return prescription;
    }
}
