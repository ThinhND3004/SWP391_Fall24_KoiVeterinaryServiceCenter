package com.example.swp391_fall24_be.entities;

import com.example.swp391_fall24_be.apis.accounts.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "ponds")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NotBlank
public class PondEntity {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Account customerID;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "size_square_meters")
    private float sizeSquareMeters;

    @Column(name = "depth_meters")
    private float depthMeters;

    @Column(name = "water_type")
    private String waterType;

    @Column(name = "temperature_celsius")
    private float temperatureCelsius;

    @Column(name = "pH_level")
    private float pHLevel;

    @Column(name = "last_maintenance_date")
    private LocalDateTime lastMaintenanceDate;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;
}
