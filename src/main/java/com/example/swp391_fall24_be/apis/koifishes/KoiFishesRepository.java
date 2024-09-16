package com.example.swp391_fall24_be.apis.koifishes;

import com.example.swp391_fall24_be.entities.KoiFishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KoiFishesRepository extends JpaRepository<KoiFishEntity, UUID> {

}
