package com.example.swp391_fall24_be.apis.timetables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TimetableRepository extends JpaRepository<TimetableEntity, String> {
    void deleteAllByVeterinarianId(String veterinarian_id);
    Optional<List<TimetableEntity>> findByVeterinarianId(String veterinarian_id);
}
