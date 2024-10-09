package com.example.swp391_fall24_be.deleteAPIs.prescription_medicine.dtos;

import com.example.swp391_fall24_be.deleteAPIs.prescription_medicine.PrescriptionMedicine;
import com.example.swp391_fall24_be.core.AbstractPagination;

public class PaginatePrescriptionMedicineDto extends AbstractPagination<PrescriptionMedicine> {
    public PaginatePrescriptionMedicineDto(Integer page, Integer unitPerPage) {
        super(page, unitPerPage);
    }

    @Override
    public PrescriptionMedicine toEntity() {
        return null;
    }
}
