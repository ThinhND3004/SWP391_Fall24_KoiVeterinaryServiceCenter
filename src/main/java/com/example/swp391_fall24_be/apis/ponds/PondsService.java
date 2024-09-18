package com.example.swp391_fall24_be.apis.ponds;

import com.example.swp391_fall24_be.apis.ponds.dto.CreatePondDto;
import com.example.swp391_fall24_be.apis.ponds.dto.PaginatePondDto;
import com.example.swp391_fall24_be.apis.ponds.dto.UpdatePondDto;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PondsService extends AbstractService<Pond, UUID, CreatePondDto, UpdatePondDto, PaginatePondDto> {
    private final PondsRepository pondsRepository;

    public PondsService(PondsRepository pondsRepository) {
        this.pondsRepository = pondsRepository;
    }


    @Override
    protected void beforeCreate(Pond entity) throws ProjectException {

    }

    @Override
    protected void beforeUpdate(Pond oldEntity, Pond newEntity) throws ProjectException {

    }

    @Override
    public Pond delete(UUID id) throws ProjectException {
        return null;
    }
}
