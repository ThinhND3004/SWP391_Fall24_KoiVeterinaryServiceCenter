package com.example.swp391_fall24_be.apis.medicine;

import com.example.swp391_fall24_be.apis.medicine.dtos.CreateMedicineDto;
import com.example.swp391_fall24_be.apis.medicine.dtos.PaginateMedicineDto;
import com.example.swp391_fall24_be.apis.medicine.dtos.UpdateMedicineDto;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.stereotype.Service;

@Service
public class MedicineService extends AbstractService<
        MedicineEntity,
        String,
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
    public MedicineEntity delete(String id) throws ProjectException {
        return null;
    }
}
