package com.example.swp391_fall24_be.apis.medicien_batch;

import com.example.swp391_fall24_be.apis.medicien_batch.dtos.CreateMedicienBatchDto;
import com.example.swp391_fall24_be.apis.medicien_batch.dtos.PaginateMedicineBatchDto;
import com.example.swp391_fall24_be.apis.medicien_batch.dtos.UpdateMedicineBatchDto;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MedicientBatchService extends AbstractService<
        MedicineBatchEntity,
        UUID,
        CreateMedicienBatchDto,
        UpdateMedicineBatchDto,
        PaginateMedicineBatchDto
> {
    @Override
    protected void beforeCreate(MedicineBatchEntity entity) throws ProjectException {

    }

    @Override
    protected void beforeUpdate(MedicineBatchEntity oldEntity, MedicineBatchEntity newEntity) throws ProjectException {

    }

    @Override
    public MedicineBatchEntity delete(UUID id) throws ProjectException {
        return null;
    }
}
