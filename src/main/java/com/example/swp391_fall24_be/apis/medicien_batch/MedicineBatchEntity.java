package com.example.swp391_fall24_be.apis.medicien_batch;

import com.example.swp391_fall24_be.apis.medicien_batch.dtos.MedicienBatchDto;
import com.example.swp391_fall24_be.apis.medicine.MedicineEntity;
import com.example.swp391_fall24_be.core.IObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "medicine_batches")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MedicineBatchEntity implements IObject<MedicienBatchDto> {
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
    public MedicienBatchDto toResponseDto() {
        MedicienBatchDto dto = new MedicienBatchDto();
        dto.setMedicineEntity(medicine);
        dto.setQuantity(quantity);
        dto.setExpirationDate(expirationDate);
        dto.setReceivedAt(receivedAt);
        return dto;
    }
}
