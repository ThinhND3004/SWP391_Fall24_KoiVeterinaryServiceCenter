package com.example.swp391_fall24_be.apis.ponds;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PondsRepository extends JpaRepository<PondEntity, String> {

}
