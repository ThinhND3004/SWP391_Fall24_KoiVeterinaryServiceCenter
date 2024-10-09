package com.example.swp391_fall24_be.apis.koispecies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KoiSpeciesRepository extends JpaRepository<KoiSpeciesEntity, String> {

}
