package com.example.swp391_fall24_be.apis.medicien_batch;

import com.example.swp391_fall24_be.apis.medicien_batch.dtos.MedicienBatchDto;
import com.example.swp391_fall24_be.apis.medicine.MedicineEntity;
import com.example.swp391_fall24_be.core.IObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private MedicineEntity medicineID;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "received_at")
    private LocalDateTime receivedAt;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Override
    public MedicienBatchDto toResponseDto() {
        MedicienBatchDto dto = new MedicienBatchDto();
        dto.setMedicineEntity(medicineID);
        dto.setQuantity(quantity);
        dto.setExpirationDate(expirationDate);
        dto.setReceivedAt(receivedAt);
        return dto;
    }
}
