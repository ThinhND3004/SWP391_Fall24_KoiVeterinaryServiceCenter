package com.example.swp391_fall24_be.apis.shipping;

import com.example.swp391_fall24_be.apis.shipping.dtos.ShippingDto;
import com.example.swp391_fall24_be.core.IObject;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "shipping")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NotBlank
public class ShippingEntity implements IObject<ShippingDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "price_per_meters", nullable = false, columnDefinition = "FLOAT")
    private float pricePerMeters;

    @Column(name = "created_at", updatable = false, columnDefinition = "DATETIME")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "DATETIME")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Override
    public ShippingDto toResponseDto() {
        ShippingDto dto = new ShippingDto();
        dto.setUpdateAt(updatedAt);
        dto.setCreateAt(createdAt);
        dto.setPricePerMeters(pricePerMeters);
        return dto;
    }

}
