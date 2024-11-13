package com.example.swp391_fall24_be.apis.prescription.dtos;

import com.example.swp391_fall24_be.apis.medicine.MedicineEntity;
import com.example.swp391_fall24_be.apis.prescription_medicine.dtos.PrescriptionMedicineDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PrescriptionDto {
    private List<PrescriptionMedicineDto> prescriptionMedicineDto;
    private float totalPrice;
    private LocalDateTime creatAt;

}
