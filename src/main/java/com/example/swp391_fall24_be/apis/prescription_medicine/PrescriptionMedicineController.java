package com.example.swp391_fall24_be.apis.prescription_medicine;

import com.example.swp391_fall24_be.apis.prescription_medicine.dtos.CreatePrescriptionMedicineDto;
import com.example.swp391_fall24_be.apis.prescription_medicine.dtos.PaginatePrescriptionMedicineDto;
import com.example.swp391_fall24_be.apis.prescription_medicine.dtos.PrescriptionMedicineDto;
import com.example.swp391_fall24_be.apis.prescription_medicine.dtos.UpdatePrescriptionMedicineDto;
import com.example.swp391_fall24_be.core.AbstractController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/prescription-medicine")
@Tag(name = "Prescription Medicine", description = "Save the amount of each medicine in prescription.")
public class PrescriptionMedicineController extends AbstractController<
        PrescriptionMedicine,
        Long,
        CreatePrescriptionMedicineDto,
        UpdatePrescriptionMedicineDto,
        PaginatePrescriptionMedicineDto,
        PrescriptionMedicineDto>{

}
