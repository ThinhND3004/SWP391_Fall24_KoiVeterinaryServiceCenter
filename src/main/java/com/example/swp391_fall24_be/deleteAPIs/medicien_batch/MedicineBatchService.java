package com.example.swp391_fall24_be.deleteAPIs.medicien_batch;

import com.example.swp391_fall24_be.deleteAPIs.medicien_batch.dtos.CreateMedicineBatchDto;
import com.example.swp391_fall24_be.deleteAPIs.medicien_batch.dtos.PaginateMedicineBatchDto;
import com.example.swp391_fall24_be.deleteAPIs.medicien_batch.dtos.UpdateMedicineBatchDto;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.stereotype.Service;

@Service
public class MedicineBatchService extends AbstractService<
        MedicineBatchEntity,
        String,
        CreateMedicineBatchDto,
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
    public MedicineBatchEntity delete(String id) throws ProjectException {
        return null;
    }
}
