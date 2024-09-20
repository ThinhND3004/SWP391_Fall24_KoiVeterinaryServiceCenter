package com.example.swp391_fall24_be.apis.images.dtos;

import com.example.swp391_fall24_be.apis.images.ImageEntity;
import com.example.swp391_fall24_be.core.IDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public class CreateImageDto implements IDto<ImageEntity> {
    @NotBlank(message = "Name is required!")
    @Size(max = 50, message = "Length of email must not exceed 50 letters!")
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "Name is required!")
    @Size(max = 200, message = "Length of local path must not exceed 200 letters!")
    @JsonProperty("localPath")
    private String localPath;

    @Override
    public ImageEntity toEntity() {
        ImageEntity image = new ImageEntity();
        image.setName(name);
        image.setLocalPath(localPath);
        return image;
    }
}
