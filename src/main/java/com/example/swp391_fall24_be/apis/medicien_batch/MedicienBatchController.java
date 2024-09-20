package com.example.swp391_fall24_be.apis.medicien_batch;

import com.example.swp391_fall24_be.apis.medicien_batch.dtos.CreateMedicineBatchDto;
import com.example.swp391_fall24_be.apis.medicien_batch.dtos.MedicienBatchDto;
import com.example.swp391_fall24_be.apis.medicien_batch.dtos.PaginateMedicineBatchDto;
import com.example.swp391_fall24_be.apis.medicien_batch.dtos.UpdateMedicineBatchDto;
import com.example.swp391_fall24_be.core.AbstractController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
@RestController
@RequestMapping("/medicien_batch")
@Tag(name = "Medicien_batches", description = "Medicien_batch APIs")
public class MedicienBatchController extends AbstractController<
        MedicineBatchEntity,
        String,
        CreateMedicineBatchDto,
        UpdateMedicineBatchDto,
        PaginateMedicineBatchDto,
        MedicienBatchDto
> {
}
