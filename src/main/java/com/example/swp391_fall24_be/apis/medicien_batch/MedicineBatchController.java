package com.example.swp391_fall24_be.apis.medicien_batch;

import com.example.swp391_fall24_be.apis.medicien_batch.dtos.CreateMedicineBatchDto;
import com.example.swp391_fall24_be.apis.medicien_batch.dtos.MedicineBatchDto;
import com.example.swp391_fall24_be.apis.medicien_batch.dtos.PaginateMedicineBatchDto;
import com.example.swp391_fall24_be.apis.medicien_batch.dtos.UpdateMedicineBatchDto;
import com.example.swp391_fall24_be.core.AbstractController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicine-batches")
@Tag(name = "Medicine batches", description = "Medicine Batches APIs")
public class MedicineBatchController extends AbstractController<
        MedicineBatchEntity,
        String,
        CreateMedicineBatchDto,
        UpdateMedicineBatchDto,
        PaginateMedicineBatchDto,
        MedicineBatchDto
> {
}
