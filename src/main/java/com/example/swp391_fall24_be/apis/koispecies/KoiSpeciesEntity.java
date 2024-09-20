package com.example.swp391_fall24_be.apis.koispecies;

import com.example.swp391_fall24_be.apis.images.ImageEntity;
import com.example.swp391_fall24_be.apis.koispecies.dto.KoiSpeciesDto;
import com.example.swp391_fall24_be.core.IObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "koi_species")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KoiSpeciesEntity implements IObject<KoiSpeciesDto> {
    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @JoinColumn(name = "picture_id")
    @OneToOne
    private ImageEntity picture;

    @Override
    public KoiSpeciesDto toResponseDto() {
        KoiSpeciesDto dto = new KoiSpeciesDto();
        dto.setId(id);
        dto.setName(name);
        dto.setCreatedAt(createdAt);
        return dto;
    }
}
