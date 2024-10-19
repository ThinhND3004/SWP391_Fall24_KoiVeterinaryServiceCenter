package com.example.swp391_fall24_be.apis.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepository extends JpaRepository<ServiceEntity, String> {
}
