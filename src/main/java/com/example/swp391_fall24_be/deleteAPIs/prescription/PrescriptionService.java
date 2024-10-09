package com.example.swp391_fall24_be.deleteAPIs.prescription;

import com.example.swp391_fall24_be.deleteAPIs.prescription.dtos.CreatePrescriptionDto;
import com.example.swp391_fall24_be.deleteAPIs.prescription.dtos.PaginatePrescriptionDto;
import com.example.swp391_fall24_be.deleteAPIs.prescription.dtos.UpdatePrescriptionDto;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionService extends AbstractService<
        PrescriptionEntity,
        String,
        CreatePrescriptionDto,
        UpdatePrescriptionDto,
        PaginatePrescriptionDto
> {
    @Override
    protected void beforeCreate(PrescriptionEntity entity) throws ProjectException {

    }

    @Override
    protected void beforeUpdate(PrescriptionEntity oldEntity, PrescriptionEntity newEntity) throws ProjectException {

    }

    @Override
    public PrescriptionEntity delete(String id) throws ProjectException {
        return null;
    }
}
