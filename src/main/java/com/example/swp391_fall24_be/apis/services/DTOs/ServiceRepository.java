package com.example.swp391_fall24_be.apis.services.DTOs;

import com.example.swp391_fall24_be.apis.services.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceRepository extends JpaRepository<Service, UUID> {
}
