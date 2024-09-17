package com.example.swp391_fall24_be.apis.prescription.dtos;

import com.example.swp391_fall24_be.entities.MedicineEntity;
import com.example.swp391_fall24_be.entities.ShippingEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
public class PrescriptionDto {

    private MedicineEntity medicineEntity;
    private ShippingEntity shippingEntity;
    private float totalPrice;
    private LocalDateTime creatAt;

}
