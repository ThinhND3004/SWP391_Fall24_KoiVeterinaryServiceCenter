package com.example.swp391_fall24_be.apis.ponds;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PondsRepository extends JpaRepository<PondEntity, String> {

}
