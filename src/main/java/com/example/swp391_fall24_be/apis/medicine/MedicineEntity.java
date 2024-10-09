package com.example.swp391_fall24_be.apis.medicine;

import com.example.swp391_fall24_be.apis.medicine.dtos.MedicineDto;
import com.example.swp391_fall24_be.apis.prescription_medicine.PrescriptionMedicine;
import com.example.swp391_fall24_be.core.IObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "medicines")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MedicineEntity implements IObject<MedicineDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "id", nullable = false)
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

    @Column(nullable = false, name = "updated_at", updatable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "medicine")
    private Set<PrescriptionMedicine> prescriptionMedicines;

    @Override
    public MedicineDto toResponseDto() {
        MedicineDto dto = new MedicineDto();
        dto.setName(name);
        dto.setDescription(description);
        dto.setPrice(price);
        dto.setManufacturer(manufacturer);
//        dto.setPrescription(prescription);
        dto.setCreatedAt(createdAt);
        dto.setUpdatedAt(updatedAt);
        return dto;
    }
}
