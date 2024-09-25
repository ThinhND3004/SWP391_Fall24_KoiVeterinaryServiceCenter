package com.example.swp391_fall24_be.apis.timetables;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TimetableRepository extends JpaRepository<Timetable, UUID> {
}
