package com.example.swp391_fall24_be.apis.koispecies;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KoiSpeciesRepository extends JpaRepository<KoiSpecies, UUID> {

}
