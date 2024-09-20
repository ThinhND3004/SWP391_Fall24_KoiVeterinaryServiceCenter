package com.example.swp391_fall24_be.apis.koispecies;

import com.example.swp391_fall24_be.apis.images.ImageEntity;
import com.example.swp391_fall24_be.apis.koispecies.dto.KoiSpeciesDto;
import com.example.swp391_fall24_be.core.IObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "koi_species")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KoiSpeciesEntity implements IObject<KoiSpeciesDto> {
    @Id
    private String id;

    @Column(nullable = false, name = "name", columnDefinition = "VARCHAR(20)")
    private String name;


    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @JoinColumn(name = "picture_id")
    @OneToOne
    private ImageEntity picture;

    @Column(nullable = false, updatable = false, name = "create_at", columnDefinition = "DATETIME")
    @CreatedDate
    private LocalDateTime createAt;

    @Column(name = "update_at", columnDefinition = "DATETIME")
    private LocalDateTime updateAt;

    @Override
    public KoiSpeciesDto toResponseDto() {
        KoiSpeciesDto dto = new KoiSpeciesDto();
        dto.setId(id);
        dto.setName(name);
        dto.setCreatedAt(createAt);
        return dto;
    }
}
