package com.example.swp391_fall24_be.deleteAPIs.medicine;

import com.example.swp391_fall24_be.deleteAPIs.medicine.dtos.CreateMedicineDto;
import com.example.swp391_fall24_be.deleteAPIs.medicine.dtos.MedicineDto;
import com.example.swp391_fall24_be.deleteAPIs.medicine.dtos.PaginateMedicineDto;
import com.example.swp391_fall24_be.deleteAPIs.medicine.dtos.UpdateMedicineDto;
import com.example.swp391_fall24_be.core.AbstractController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicine")
@Tag(name = "Medicines", description = "Medicine APIs")
public class MedicineController extends AbstractController<
        MedicineEntity,
        String,
        CreateMedicineDto,
        UpdateMedicineDto,
        PaginateMedicineDto,
        MedicineDto
> {
}
