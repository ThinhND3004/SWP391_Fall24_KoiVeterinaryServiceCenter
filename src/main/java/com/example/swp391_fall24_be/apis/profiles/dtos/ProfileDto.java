package com.example.swp391_fall24_be.apis.profiles.dtos;

import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
public class ProfileDto {
    private String certification;
    private Integer yearOfExperience;
    private String education;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

}