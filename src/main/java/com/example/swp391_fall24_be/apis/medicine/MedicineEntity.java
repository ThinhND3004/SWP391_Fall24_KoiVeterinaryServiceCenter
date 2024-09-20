package com.example.swp391_fall24_be.apis.medicine;

import com.example.swp391_fall24_be.apis.medicine.dtos.MedicineDto;
import com.example.swp391_fall24_be.apis.prescription.PrescriptionEntity;
import com.example.swp391_fall24_be.core.IObject;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity(name = "medicines")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MedicineEntity implements IObject<MedicineDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, name = "name", columnDefinition = "VARCHAR(20)")
    private String name;

    @Column(nullable = false, name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, name = "manufacturer", columnDefinition = "VARCHAR(50)")
    private String manufacturer;

    @Column(nullable = false, name = "price", columnDefinition = "FLOAT")
    private float price;

    @Column(nullable = false, name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
            name = "medicine_prescription",
            joinColumns = @JoinColumn(name = "medicine_id"),
            inverseJoinColumns = @JoinColumn(name = "prescription_id")
    )
    private Set<PrescriptionEntity> prescriptionEntities;

    @Override
    public MedicineDto toResponseDto() {
        MedicineDto dto = new MedicineDto();
        dto.setName(name);
        dto.setDescription(description);
        dto.setPrice(price);
        dto.setManufacturer(manufacturer);
        dto.setPrescriptionEntities(prescriptionEntities);
        dto.setCreatedAt(createdAt);
        return dto;
    }
}
