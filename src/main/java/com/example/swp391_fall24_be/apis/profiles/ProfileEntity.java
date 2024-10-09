package com.example.swp391_fall24_be.apis.profiles;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.profiles.DTOs.ProfileDTO;
import com.example.swp391_fall24_be.core.IObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity(name = "profiles")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@Getter
@Setter
public class ProfileEntity implements IObject<ProfileDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "full_name", nullable = false, columnDefinition = "NVARCHAR(100)")
    private String fullName;

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

    @Override
    public ProfileDTO toResponseDto() {
        return null;
    }
}
