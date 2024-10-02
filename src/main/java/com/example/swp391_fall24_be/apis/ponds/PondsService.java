package com.example.swp391_fall24_be.apis.ponds;

import com.example.swp391_fall24_be.apis.ponds.dto.CreatePondDto;
import com.example.swp391_fall24_be.apis.ponds.dto.PaginatePondDto;
import com.example.swp391_fall24_be.apis.ponds.dto.UpdatePondDto;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PondsService extends AbstractService<PondEntity, String, CreatePondDto, UpdatePondDto, PaginatePondDto> {
    @Autowired
    private final PondsRepository pondsRepository;

    public PondsService(PondsRepository pondsRepository) {
        this.pondsRepository = pondsRepository;
    }


    @Override
    protected void beforeCreate(PondEntity entity) throws ProjectException {

    }

    @Override
    protected void beforeUpdate(PondEntity oldEntity, PondEntity newEntity) throws ProjectException {

    }

    @Override
    public PondEntity delete(String id) throws ProjectException {
        return null;
    }
}
