package com.example.swp391_fall24_be.apis.koispecies;

import com.example.swp391_fall24_be.apis.koispecies.dto.CreateKoiSpeciesDto;
import com.example.swp391_fall24_be.apis.koispecies.dto.PaginateKoiSpeciesDto;
import com.example.swp391_fall24_be.apis.koispecies.dto.UpdateKoiSpeciesDto;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.stereotype.Service;

@Service
public class KoiSpeciesService extends AbstractService<KoiSpeciesEntity, String, CreateKoiSpeciesDto, UpdateKoiSpeciesDto, PaginateKoiSpeciesDto> {
    private final KoiSpeciesRepository koiSpeciesRepository;

    public KoiSpeciesService(KoiSpeciesRepository koiSpeciesRepository) {
        this.koiSpeciesRepository = koiSpeciesRepository;
    }


    @Override
    protected void beforeCreate(KoiSpeciesEntity entity) throws ProjectException {

    }

    @Override
    protected void beforeUpdate(KoiSpeciesEntity oldEntity, KoiSpeciesEntity newEntity) throws ProjectException {

    }

    @Override
    public KoiSpeciesEntity delete(String id) throws ProjectException {
        return null;
    }
}
