package com.example.swp391_fall24_be.apis.timetables;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TimetableRepository extends JpaRepository<Timetable, String> {
    void deleteAllByVeterianId(String veterian_id);
    Optional<List<Timetable>> findByVeterianId(String veterian_id);
}
