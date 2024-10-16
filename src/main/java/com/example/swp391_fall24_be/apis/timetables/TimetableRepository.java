package com.example.swp391_fall24_be.apis.timetables;

import com.example.swp391_fall24_be.apis.profiles.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

public interface TimetableRepository extends JpaRepository<TimetableEntity, String> {
    void deleteAllByProfileId(String veterian_id);
    Optional<List<TimetableEntity>> findByProfileId(String veterian_id);

    List<TimetableEntity> findByProfileAndDayOfWeekOrderByStartTimeAsc(ProfileEntity profile, DayOfWeek dayOfWeek);

}
