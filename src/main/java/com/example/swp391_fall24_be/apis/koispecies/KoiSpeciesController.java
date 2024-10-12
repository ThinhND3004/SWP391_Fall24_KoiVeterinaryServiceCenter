package com.example.swp391_fall24_be.apis.koispecies;

import com.example.swp391_fall24_be.apis.koispecies.dto.CreateKoiSpeciesDto;
import com.example.swp391_fall24_be.apis.koispecies.dto.KoiSpeciesDto;
import com.example.swp391_fall24_be.apis.koispecies.dto.PaginateKoiSpeciesDto;
import com.example.swp391_fall24_be.apis.koispecies.dto.UpdateKoiSpeciesDto;
import com.example.swp391_fall24_be.core.AbstractController;
import com.example.swp391_fall24_be.core.ResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/koi-species")
@Tag(name = "KoiSpecies", description = "Koi Species APIs")
public class KoiSpeciesController extends AbstractController<KoiSpeciesEntity, String, CreateKoiSpeciesDto, UpdateKoiSpeciesDto, PaginateKoiSpeciesDto, KoiSpeciesDto> {
    private final KoiSpeciesService koiSpeciesService;

    public KoiSpeciesController(KoiSpeciesService koiSpeciesService) {
        this.koiSpeciesService = koiSpeciesService;
    }

    @Override
    public ResponseDto<List<KoiSpeciesDto>> doGetMany(PaginateKoiSpeciesDto paginateKoiSpeciesDto) {
        return super.doGetMany(paginateKoiSpeciesDto);
    }

    @Override
    public ResponseDto<KoiSpeciesDto> doGet(String id) {
        return super.doGet(id);
    }

    @Override
    public ResponseDto<KoiSpeciesDto> doPost(CreateKoiSpeciesDto dto) {
        return super.doPost(dto);
    }

    @Override
    public ResponseDto<KoiSpeciesDto> doPut(String id, UpdateKoiSpeciesDto dto) {
        return super.doPut(id, dto);
    }

    @Override
    public ResponseDto<KoiSpeciesDto> doDelete(String id) {
        return super.doDelete(id);
    }
}
