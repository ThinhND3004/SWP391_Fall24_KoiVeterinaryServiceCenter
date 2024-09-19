package com.example.swp391_fall24_be.apis.images;

import com.example.swp391_fall24_be.apis.images.dtos.ImageDto;
import com.example.swp391_fall24_be.core.IObject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "images")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Image implements IObject<ImageDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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

    @Override
    public ImageDto toResponseDto() {
        ImageDto dto = new ImageDto();
        dto.setName(name);
        dto.setLocalPath(localPath);
        dto.setUpdatedAt(updateAt);
        dto.setCreatedAt(createdAt);
        return null;
    }
}
