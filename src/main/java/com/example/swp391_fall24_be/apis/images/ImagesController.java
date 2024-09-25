package com.example.swp391_fall24_be.apis.images;

import com.example.swp391_fall24_be.apis.images.dtos.CreateImageDto;
import com.example.swp391_fall24_be.apis.images.dtos.ImageDto;
import com.example.swp391_fall24_be.apis.images.dtos.PaginateImageDto;
import com.example.swp391_fall24_be.apis.images.dtos.UpdateImageDto;
import com.example.swp391_fall24_be.core.AbstractController;
import com.example.swp391_fall24_be.core.ProjectException;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
@MultipartConfig(fileSizeThreshold = 20971520)
@Tag(name = "Images", description = "Upload image")
public class ImagesController extends AbstractController
        <ImageEntity,
        String,
        CreateImageDto,
        UpdateImageDto,
        PaginateImageDto,
        ImageDto
        >{

    private final ImagesService service;

    public ImagesController(ImagesService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    @ApiResponse(description = "Upload image", headers = {
            @Header(name = "content-type", description = "application/multipart")
    })
    public ImageDto uploadImage(@RequestBody MultipartFile multipartFile) throws ProjectException {
        return service.upload(multipartFile);
    }

    @ResponseBody
    @ApiResponse(description = "Get image", headers = {
            @Header(name = "content-type", description = "image/jpeg")
    })
    @GetMapping(value = "/{id}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public byte[] getImage(@PathVariable String id) throws ProjectException {
        return service.getImage(id);
    }
}
