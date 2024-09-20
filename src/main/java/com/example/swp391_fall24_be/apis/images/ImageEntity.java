package com.example.swp391_fall24_be.apis.images;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.images.dtos.ImageDto;
import com.example.swp391_fall24_be.apis.koispecies.KoiSpeciesEntity;
import com.example.swp391_fall24_be.core.IObject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity(name = "images")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class ImageEntity implements IObject<ImageDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false, columnDefinition = "NVARCHAR(50)")
    private String name;

    @Column(name = "local_path", nullable = false, columnDefinition = "TEXT")
    private String localPath;

    @Column (name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column (name = "updated_at", nullable = false)
    @LastModifiedDate
    private LocalDateTime updateAt;

    @OneToOne(mappedBy = "avatar")
    private AccountEntity account;

    @OneToOne(mappedBy = "picture")
    private KoiSpeciesEntity koiSpecies;

    @Override
    public ImageDto toResponseDto() {
        ImageDto dto = new ImageDto();
        dto.setId(id);
        dto.setName(name);
        dto.setLocalPath(localPath);
        dto.setUpdatedAt(updateAt);
        dto.setCreatedAt(createdAt);
        return null;
    }
}
