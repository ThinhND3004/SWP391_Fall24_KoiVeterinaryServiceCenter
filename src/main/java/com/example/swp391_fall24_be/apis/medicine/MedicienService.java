package com.example.swp391_fall24_be.apis.medicine;

import com.example.swp391_fall24_be.apis.medicine.dtos.CreateMedicineDto;
import com.example.swp391_fall24_be.apis.medicine.dtos.PaginateMedicineDto;
import com.example.swp391_fall24_be.apis.medicine.dtos.UpdateMedicineDto;
import com.example.swp391_fall24_be.core.AbstractPagination;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.IDto;
import com.example.swp391_fall24_be.core.ProjectException;

import java.util.List;
import java.util.UUID;

public class MedicienService extends AbstractService<
        MedicineEntity,
        UUID,
        CreateMedicineDto,
        UpdateMedicineDto,
        PaginateMedicineDto
> {

    @Override
    protected void beforeCreate(MedicineEntity entity) throws ProjectException {

    }

    @Override
    protected void beforeUpdate(MedicineEntity oldEntity, MedicineEntity newEntity) throws ProjectException {

    }

    @Override
    public MedicineEntity delete(UUID id) throws ProjectException {
        return null;
    }
}
