package com.example.swp391_fall24_be.apis.koispecies;

import com.example.swp391_fall24_be.apis.koispecies.dto.CreateKoiSpeciesDto;
import com.example.swp391_fall24_be.apis.koispecies.dto.PaginateKoiSpeciesDto;
import com.example.swp391_fall24_be.apis.koispecies.dto.UpdateKoiSpeciesDto;
import com.example.swp391_fall24_be.core.AbstractService;
import com.example.swp391_fall24_be.core.ProjectException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KoiSpeciesService extends AbstractService<KoiSpecies, UUID, CreateKoiSpeciesDto, UpdateKoiSpeciesDto, PaginateKoiSpeciesDto> {
    private final KoiSpeciesRepository koiSpeciesRepository;

    public KoiSpeciesService(KoiSpeciesRepository koiSpeciesRepository) {
        this.koiSpeciesRepository = koiSpeciesRepository;
    }


    @Override
    protected void beforeCreate(KoiSpecies entity) throws ProjectException {

    }

    @Override
    protected void beforeUpdate(KoiSpecies oldEntity, KoiSpecies newEntity) throws ProjectException {

    }

    @Override
    public KoiSpecies delete(UUID id) throws ProjectException {
        return null;
    }
}
