package com.example.swp391_fall24_be.apis.timetables;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.timetables.DTOs.TimetableDTO;
import com.example.swp391_fall24_be.core.IObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity(name = "timetables")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Timetable implements IObject<TimetableDTO> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    @ManyToOne
    @JoinColumn(name = "veterinarian_id", nullable = false)
    @JsonProperty("veterinarian_id")
    private AccountEntity veterian;

    @Column(name = "day_of_week", nullable = false)
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Override
    public TimetableDTO toResponseDto() {
        TimetableDTO timetableDTO = new TimetableDTO();
        timetableDTO.setId(id);
        timetableDTO.setVeterinarianId(veterian.getId());
        timetableDTO.setStartTime(startTime);
        timetableDTO.setEndTime(endTime);
        timetableDTO.setCreatedAt(createdAt);
        timetableDTO.setUpdatedAt(updatedAt);
        return timetableDTO;
    }
}
