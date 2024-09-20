package com.example.swp391_fall24_be.apis.prescription_medicine;

import com.example.swp391_fall24_be.apis.prescription_medicine.dtos.CreatePrescriptionMedicineDto;
import com.example.swp391_fall24_be.apis.prescription_medicine.dtos.PaginatePrescriptionMedicineDto;
import com.example.swp391_fall24_be.apis.prescription_medicine.dtos.UpdatePrescriptionMedicineDto;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PrescriptionMedicineService extends AbstractService<
        PrescriptionMedicine,
        Long,
        CreatePrescriptionMedicineDto,
        UpdatePrescriptionMedicineDto,
        PaginatePrescriptionMedicineDto> {

    @Override
    protected void beforeCreate(PrescriptionMedicine entity) throws ProjectException {

    }

    @Override
    protected void beforeUpdate(PrescriptionMedicine oldEntity, PrescriptionMedicine newEntity) throws ProjectException {

    }

    @Override
    public PrescriptionMedicine delete(Long id) throws ProjectException {
        return null;
    }
}
