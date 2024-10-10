package com.example.swp391_fall24_be.apis.images;

import com.example.swp391_fall24_be.apis.images.dtos.CreateImageDto;
import com.example.swp391_fall24_be.apis.images.dtos.ImageDto;
import com.example.swp391_fall24_be.apis.images.dtos.PaginateImageDto;
import com.example.swp391_fall24_be.apis.images.dtos.UpdateImageDto;
import com.example.swp391_fall24_be.core.AbstractController;
import com.example.swp391_fall24_be.core.ProjectException;
import com.example.swp391_fall24_be.core.ResponseDto;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/images")
@CrossOrigin
@Tag(name = "Images", description = "Upload image")
public class ImagesController extends AbstractController
        <ImageEntity,
        String,
        CreateImageDto,
        UpdateImageDto,
        PaginateImageDto,
        ImageDto
        >{

    protected ImagesService service;

    public ImagesController(ImagesService service) {
        this.service = service;
    }

    @ApiResponse(description = "Upload image", headers = {
            @Header(name = "content-type", description = "multipart/form-data")
    })
    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseDto<ImageDto> uploadImage(@RequestBody MultipartFile multipartFile) throws ProjectException {
        try {
            return new ResponseDto<>(
                    HttpStatus.OK.value(),
                    "Get one successfully!",
                    service.upload(multipartFile),
                    null
            );
        }
        catch (Exception e){
            List<String> errorList = new ArrayList<>();
            errorList.add(e.getMessage());
            return new ResponseDto<>(
                    HttpStatus.BAD_REQUEST.value(),
                    "Cannot upload image!",
                    null,
                    errorList
            );
        }
    }

    @ResponseBody
    @ApiResponse(description = "Get image", headers = {
            @Header(name = "content-type", description = "image/jpeg")
    })
    @GetMapping(value = "/get/{id}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public byte[] getImage(@PathVariable String id) throws ProjectException {
        return service.getImage(id);
    }
}
