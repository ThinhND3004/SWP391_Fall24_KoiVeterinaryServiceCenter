package com.example.swp391_fall24_be.apis.ponds;

import com.example.swp391_fall24_be.entities.PondEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PondsRepository extends JpaRepository<PondEntity, UUID> {

}
