package com.example.swp391_fall24_be.apis.medicine.dtos;

import com.example.swp391_fall24_be.apis.medicine.MedicineEntity;
import com.example.swp391_fall24_be.core.AbstractPagination;

import java.beans.ConstructorProperties;

public class PaginateMedicineDto extends AbstractPagination<MedicineEntity> {

    @ConstructorProperties({"page", "unitPerPage"})
    public PaginateMedicineDto(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }
    @Override
    public MedicineEntity toEntity() {
        return new MedicineEntity();
    }
}
