package com.example.swp391_fall24_be.apis.koifishes;

import com.example.swp391_fall24_be.apis.koifishes.dto.CreateKoiFishDto;
import com.example.swp391_fall24_be.apis.koifishes.dto.PaginateKoiFishDto;
import com.example.swp391_fall24_be.apis.koifishes.dto.UpdateKoiFishDto;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KoiFishesService extends AbstractService<KoiFish, UUID, CreateKoiFishDto, UpdateKoiFishDto, PaginateKoiFishDto> {
    private final KoiFishesRepository koiFishesRepository;

    public KoiFishesService(KoiFishesRepository koiFishesRepository) {
        this.koiFishesRepository = koiFishesRepository;
    }

    @Override
    protected void beforeCreate(KoiFish entity) throws ProjectException {

    }

    @Override
    protected void beforeUpdate(KoiFish oldEntity, KoiFish newEntity) throws ProjectException {

    }

    @Override
    public KoiFish delete(UUID id) throws ProjectException {
        return null;
    }
}
