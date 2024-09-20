package com.example.swp391_fall24_be.apis.images;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImagesRepository extends JpaRepository<ImageEntity, String> {
}
