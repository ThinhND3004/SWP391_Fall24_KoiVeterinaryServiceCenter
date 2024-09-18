package com.example.swp391_fall24_be.apis.treatments;

import com.example.swp391_fall24_be.apis.treatments.dto.CreateTreatmentDto;
import com.example.swp391_fall24_be.apis.treatments.dto.PaginateTreatmentDto;
import com.example.swp391_fall24_be.apis.treatments.dto.TreatmentDto;
import com.example.swp391_fall24_be.apis.treatments.dto.UpdateTreatmentDto;
import com.example.swp391_fall24_be.core.AbstractController;
import com.example.swp391_fall24_be.core.ResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/treatments")
@Tag(name = "Treatments", description = "Treatment APIs")
public class TreatmentsController extends AbstractController<Treatment, UUID, CreateTreatmentDto, UpdateTreatmentDto, PaginateTreatmentDto, TreatmentDto> {
    private final TreatmentsService treatmentsService;

    public TreatmentsController(TreatmentsService treatmentsService) {
        this.treatmentsService = treatmentsService;
    }

    @Override
    public ResponseDto<List<TreatmentDto>> doGetMany(PaginateTreatmentDto paginateTreatmentDto) {
        return super.doGetMany(paginateTreatmentDto);
    }

    @Override
    public ResponseDto<TreatmentDto> doGet(UUID id) {
        return super.doGet(id);
    }

    @Override
    public ResponseDto<TreatmentDto> doPost(CreateTreatmentDto dto) {
        return super.doPost(dto);
    }

    @Override
    public ResponseDto<TreatmentDto> doPut(UUID id, UpdateTreatmentDto dto) {
        return super.doPut(id, dto);
    }

    @Override
    public ResponseDto<TreatmentDto> doDelete(UUID id) {
        return super.doDelete(id);
    }
}
