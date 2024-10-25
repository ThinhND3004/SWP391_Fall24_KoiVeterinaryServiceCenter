package com.example.swp391_fall24_be.apis.ponds;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.ponds.dto.PondDto;
import com.example.swp391_fall24_be.core.IObject;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "ponds")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class PondEntity implements IObject<PondDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private AccountEntity customer;

    @Column(name = "name")
    private String name;

    @Column(name = "size_square_meters")
    private Float sizeSquareMeters;

    @Column(name = "depth_meters")
    private Float depthMeters;

    @Column(name = "water_type")
    private String waterType;

    @Column(name = "temperature_celsius")
    private Float temperatureCelsius;

    @Column(name = "pH_level")
    private Float pHLevel;

    @Column(name = "last_maintenance_date")
    private LocalDate lastMaintenanceDate;

    @Column(name = "date_created")
    @CreatedDate
    private LocalDateTime dateCreated;

    @Override
    public PondDto toResponseDto() {
        PondDto pondDto = new PondDto();
        pondDto.setName(name);
        pondDto.setDepthMeters(depthMeters);
        pondDto.setWaterType(waterType);
        pondDto.setSizeSquareMeters(sizeSquareMeters);
        pondDto.setTemperatureCelsius(temperatureCelsius);
        pondDto.setPHLevel(pHLevel);
        pondDto.setLastMaintenanceDate(lastMaintenanceDate);
        pondDto.setDateCreated(dateCreated);
        return pondDto;
    }
}
