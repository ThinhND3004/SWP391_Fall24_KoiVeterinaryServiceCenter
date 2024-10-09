package com.example.swp391_fall24_be.deleteAPIs.medicien_batch;

import com.example.swp391_fall24_be.deleteAPIs.medicien_batch.dtos.MedicineBatchDto;
import com.example.swp391_fall24_be.deleteAPIs.medicine.MedicineEntity;
import com.example.swp391_fall24_be.core.IObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "medicine_batches")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MedicineBatchEntity implements IObject<MedicineBatchDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private MedicineEntity medicine;

    @Column(nullable = false, name = "quantity", columnDefinition = "INT")
    private int quantity;

    @Column(nullable = false, name = "received_at", columnDefinition = "DATETIME")
    @CreatedDate
    private LocalDateTime receivedAt;

    @Column(nullable = false, name = "expiration_date", columnDefinition = "DATE")
    private LocalDate expirationDate;

    @Override
    public MedicineBatchDto toResponseDto() {
        MedicineBatchDto dto = new MedicineBatchDto();
        dto.setMedicineEntity(medicine);
        dto.setQuantity(quantity);
        dto.setExpirationDate(expirationDate);
        dto.setReceivedAt(receivedAt);
        return dto;
    }
}
