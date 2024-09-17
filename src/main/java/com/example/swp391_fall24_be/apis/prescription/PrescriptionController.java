package com.example.swp391_fall24_be.apis.prescription;

import com.example.swp391_fall24_be.apis.accounts.Account;
import com.example.swp391_fall24_be.apis.accounts.dtos.AccountDto;
import com.example.swp391_fall24_be.apis.accounts.dtos.CreateAccountDto;
import com.example.swp391_fall24_be.apis.accounts.dtos.PaginateAccountDto;
import com.example.swp391_fall24_be.apis.accounts.dtos.UpdateAccountDto;
import com.example.swp391_fall24_be.apis.prescription.dtos.CreatePrescriptionDto;
import com.example.swp391_fall24_be.apis.prescription.dtos.PaginatePrescriptionDto;
import com.example.swp391_fall24_be.apis.prescription.dtos.PrescriptionDto;
import com.example.swp391_fall24_be.apis.prescription.dtos.UpdatePrescriptionDto;
import com.example.swp391_fall24_be.core.AbstractController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/prescription")
@Tag(name = "Prescriptions", description = "Prescription APIs")
public class PrescriptionController extends AbstractController<
        PrescriptionEntity,
        UUID,
        CreatePrescriptionDto,
        UpdatePrescriptionDto,
        PaginatePrescriptionDto,
        PrescriptionDto
> {

}
