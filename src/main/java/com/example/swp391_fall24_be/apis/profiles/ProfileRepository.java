package com.example.swp391_fall24_be.apis.profiles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

public interface ProfileRepository extends JpaRepository<ProfileEntity, String> {
    ProfileEntity findByAccountId(String id);

    @Query("SELECT p FROM profiles p JOIN p.timetables t WHERE :searchTime > t.startTime AND :searchTime < t.endTime")
    List<ProfileEntity> findProfilesWithSuitableTimetables(LocalTime searchTime);
}
