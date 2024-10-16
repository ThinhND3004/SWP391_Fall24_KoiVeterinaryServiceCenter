package com.example.swp391_fall24_be.apis.prescription;

import com.example.swp391_fall24_be.apis.prescription.dtos.CreatePrescriptionDto;
import com.example.swp391_fall24_be.apis.prescription.dtos.PaginatePrescriptionDto;
import com.example.swp391_fall24_be.apis.prescription.dtos.PrescriptionDto;
import com.example.swp391_fall24_be.apis.prescription.dtos.UpdatePrescriptionDto;
import com.example.swp391_fall24_be.core.AbstractController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prescriptions")
@Tag(name = "Prescriptions", description = "Prescription APIs")
public class PrescriptionController extends AbstractController<
        PrescriptionEntity,
        String,
        CreatePrescriptionDto,
        UpdatePrescriptionDto,
        PaginatePrescriptionDto,
        PrescriptionDto
> {

}
