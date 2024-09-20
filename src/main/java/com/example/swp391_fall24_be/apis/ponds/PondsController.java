package com.example.swp391_fall24_be.apis.ponds;

import com.example.swp391_fall24_be.apis.ponds.dto.CreatePondDto;
import com.example.swp391_fall24_be.apis.ponds.dto.PaginatePondDto;
import com.example.swp391_fall24_be.apis.ponds.dto.PondDto;
import com.example.swp391_fall24_be.apis.ponds.dto.UpdatePondDto;
import com.example.swp391_fall24_be.core.AbstractController;
import com.example.swp391_fall24_be.core.ResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ponds")
@Tag(name = "Ponds", description = "Pond APIs")
public class PondsController extends AbstractController<PondEntity, String, CreatePondDto, UpdatePondDto, PaginatePondDto, PondDto> {
    private final PondsService pondsService;

    public PondsController(PondsService pondsService) {
        this.pondsService = pondsService;
    }

    @Override
    public ResponseDto<List<PondDto>> doGetMany(PaginatePondDto paginatePondDto) {
        return super.doGetMany(paginatePondDto);
    }

    @Override
    public ResponseDto<PondDto> doGet(String id) {
        return super.doGet(id);
    }

    @Override
    public ResponseDto<PondDto> doPost(CreatePondDto dto) {
        return super.doPost(dto);
    }

    @Override
    public ResponseDto<PondDto> doPut(String id, UpdatePondDto dto) {
        return super.doPut(id, dto);
    }

    @Override
    public ResponseDto<PondDto> doDelete(String id) {
        return super.doDelete(id);
    }
}
