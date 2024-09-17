package com.example.swp391_fall24_be.apis.shipping;

import com.example.swp391_fall24_be.entities.ShippingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShippingRepository extends JpaRepository<UUID, ShippingEntity> {
}
