package com.example.swp391_fall24_be.apis.medicine.dtos;

import com.example.swp391_fall24_be.apis.medicine.MedicineEntity;
import com.example.swp391_fall24_be.core.AbstractPagination;

public class PaginateMedicineDto extends AbstractPagination<MedicineEntity> {
    public PaginateMedicineDto(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public MedicineEntity toEntity() {
        return null;
    }
}
