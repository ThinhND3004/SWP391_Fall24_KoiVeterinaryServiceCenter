package com.example.swp391_fall24_be.apis.koifishes;

import com.example.swp391_fall24_be.apis.koifishes.dto.CreateKoiFishDto;
import com.example.swp391_fall24_be.apis.koifishes.dto.KoiFishDto;
import com.example.swp391_fall24_be.apis.koifishes.dto.PaginateKoiFishDto;
import com.example.swp391_fall24_be.apis.koifishes.dto.UpdateKoiFishDto;
import com.example.swp391_fall24_be.core.AbstractController;
import com.example.swp391_fall24_be.core.ResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/koi_fishes")
@Tag(name = "KoiFishes", description = "Koi Fishes APIs")
public class KoiFishesController extends AbstractController<KoiFish, UUID, CreateKoiFishDto, UpdateKoiFishDto, PaginateKoiFishDto, KoiFishDto> {
    private final KoiFishesService koiFishesService;

    public KoiFishesController(KoiFishesService koiFishesService) {
        this.koiFishesService = koiFishesService;
    }

    @Override
    public ResponseDto<List<KoiFishDto>> doGetMany(PaginateKoiFishDto paginateKoiFishDto) {
        return super.doGetMany(paginateKoiFishDto);
    }

    @Override
    public ResponseDto<KoiFishDto> doGet(UUID id) {
        return super.doGet(id);
    }

    @Override
    public ResponseDto<KoiFishDto> doPost(CreateKoiFishDto dto) {
        return super.doPost(dto);
    }

    @Override
    public ResponseDto<KoiFishDto> doPut(UUID id, UpdateKoiFishDto dto) {
        return super.doPut(id, dto);
    }

    @Override
    public ResponseDto<KoiFishDto> doDelete(UUID id) {
        return super.doDelete(id);
    }
}
