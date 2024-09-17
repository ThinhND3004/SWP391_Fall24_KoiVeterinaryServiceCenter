package com.example.swp391_fall24_be.apis.treatments;

import com.example.swp391_fall24_be.apis.treatments.dto.CreateTreatmentDto;
import com.example.swp391_fall24_be.apis.treatments.dto.PaginateTreatmentDto;
import com.example.swp391_fall24_be.apis.treatments.dto.UpdateTreatmentDto;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TreatmentsService extends AbstractService<Treatment, UUID, CreateTreatmentDto, UpdateTreatmentDto, PaginateTreatmentDto> {
    private final TreatmentsRepository treatmentsRepository;

    public TreatmentsService(TreatmentsRepository treatmentsRepository) {
        this.treatmentsRepository = treatmentsRepository;
    }

    @Override
    protected void beforeCreate(Treatment entity) throws ProjectException {

    }

    @Override
    protected void beforeUpdate(Treatment oldEntity, Treatment newEntity) throws ProjectException {

    }

    @Override
    public Treatment delete(UUID id) throws ProjectException {
        return null;
    }
}
