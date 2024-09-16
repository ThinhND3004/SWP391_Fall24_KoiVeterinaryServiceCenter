package com.example.swp391_fall24_be.entities;

import com.example.swp391_fall24_be.apis.accounts.Account;
import com.example.swp391_fall24_be.apis.koispecies.dto.KoiSpeciesDto;
import com.example.swp391_fall24_be.core.IObject;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "koi_species")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KoiSpeciesEntity implements IObject<KoiSpeciesDto> {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Account customerID;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Override
    public KoiSpeciesDto toResponseDto() {
        KoiSpeciesDto dto = new KoiSpeciesDto();
        dto.setCustomerID(customerID);
        dto.setName(name);
        dto.setCreatedAt(createdAt);
        return dto;
    }
}
