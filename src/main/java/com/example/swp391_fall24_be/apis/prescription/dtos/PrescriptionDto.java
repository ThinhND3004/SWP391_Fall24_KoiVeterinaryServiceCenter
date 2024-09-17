package com.example.swp391_fall24_be.apis.prescription.dtos;

import com.example.swp391_fall24_be.apis.medicine.MedicineEntity;
import com.example.swp391_fall24_be.apis.shipping.ShippingEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PrescriptionDto {

    private MedicineEntity medicineEntity;
    private ShippingEntity shippingEntity;
    private float totalPrice;
    private LocalDateTime creatAt;

}
