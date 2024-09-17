package com.example.swp391_fall24_be.apis.vehicle;

import com.example.swp391_fall24_be.apis.shipping.ShippingEntity;
import com.example.swp391_fall24_be.apis.vehicle.dtos.VehicleDto;
import com.example.swp391_fall24_be.core.IObject;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Entity(name = "vehicle")
//@Getter
//@Setter
@Data
public class VehicleEntity implements IObject<VehicleDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", columnDefinition = "VARCHAR(20)")
    private String name;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;


    @OneToMany
    private List<ShippingEntity> shippingEntities;

    @Override
    public VehicleDto toResponseDto() {
        VehicleDto dto = new VehicleDto();
        dto.setName(name);
        dto.setCreateAt(createAt);
        dto.setUpdateAt(updateAt);
        return dto;
    }
}
