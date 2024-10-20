package com.example.swp391_fall24_be.apis.profiles;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.profiles.dtos.ProfileDto;
import com.example.swp391_fall24_be.apis.timetables.TimetableEntity;
import com.example.swp391_fall24_be.core.IObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "profiles")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@Getter
@Setter
public class ProfileEntity implements IObject<ProfileDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "certification", nullable = false, columnDefinition = "TEXT")
    private String certification;

    @Column(name = "year_of_experience", nullable = false, columnDefinition = "INT")
    private Integer yearOfExperience;

    @Column(name = "education", nullable = false, columnDefinition = "TEXT")
    private String education;

    @Column (nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createAt;

    @Column (nullable = false)
    @LastModifiedDate
    private LocalDateTime updateAt;

    @OneToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @OneToMany(mappedBy = "profile")
    private List<TimetableEntity> timetables;

    public ProfileEntity() {

    }

    @Override
    public ProfileDto toResponseDto() {
        ProfileDto dto = new ProfileDto();
        dto.setCertification(certification);
        dto.setEducation(education);
        dto.setCreateAt(createAt);
        dto.setUpdateAt(updateAt);
        dto.setYearOfExperience(yearOfExperience);
        return dto;
    }
}