package com.example.swp391_fall24_be.apis.shipping;

import com.example.swp391_fall24_be.apis.shipping.dtos.ShippingDto;
import com.example.swp391_fall24_be.apis.vehicle.VehicleEntity;
import com.example.swp391_fall24_be.core.IObject;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private UUID id;

    @Column(name = "price_per_meters")
    private float pricePerMeters;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle;

    @Override
    public ShippingDto toResponseDto() {
        ShippingDto dto = new ShippingDto();
        dto.setVehicle(vehicle);
        dto.setUpdateAt(updatedAt);
        dto.setCreateAt(createdAt);
        dto.setPricePerMeters(pricePerMeters);
        return dto;
    }

}
