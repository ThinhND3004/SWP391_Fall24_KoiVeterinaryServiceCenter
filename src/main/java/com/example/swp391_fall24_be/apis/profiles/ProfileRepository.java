package com.example.swp391_fall24_be.apis.profiles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileEntity, String> {
    ProfileEntity findByAccountId(String id);
}
