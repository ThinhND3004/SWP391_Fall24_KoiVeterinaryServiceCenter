package com.example.swp391_fall24_be.apis.images.dtos;

import com.example.swp391_fall24_be.core.IObject;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImageDto {
    private String name;
    private String localPath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
