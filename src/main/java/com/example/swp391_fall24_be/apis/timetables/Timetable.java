package com.example.swp391_fall24_be.apis.timetables;

import com.example.swp391_fall24_be.apis.accounts.Account;
import com.example.swp391_fall24_be.apis.timetables.DTOs.TimetableDTO;
import com.example.swp391_fall24_be.core.IObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
import java.util.UUID;

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
    private Account veterinarianId;

    @Column(name = "day_of_week", nullable = false)
    @JsonProperty("day_of_week")
    private DayOfWeek dayOfWeek;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @NotNull(message = "Status is required!")
    @JsonProperty("status")
    private TimetableStatusEnum timetableStatus; // Trạng thái lịch trình

    @Column(name = "description")
    @Size(max = 255, message = "Description should not exceed 255 characters.")
    @JsonProperty("description")
    private String description; // Mô tả về lịch trình

    @Column(name = "max_appointment", nullable = false)
    @Min(0)
    @JsonProperty("max_appointment")
    private int maxAppointments; // Giới hạn số lượng buổi hẹn

    @Column(name = "location", nullable = false)
    @NotNull(message = "Location is required!")
    @Size(max = 255, message = "Location should not exceed 255 characters.")
    @JsonProperty("location")
    private String location; // Địa điểm làm việc

    @Column(name = "start_time", nullable = false)
    @NotNull(message = "Start time is required!")
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    @NotNull(message = "End time is required!")
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
        timetableDTO.setVeterinarianId(veterinarianId.getId());
        timetableDTO.setDayOfWeek(dayOfWeek);
        timetableDTO.setStartTime(startTime);
        timetableDTO.setEndTime(endTime);
        timetableDTO.setTimetableStatus(timetableStatus);
        timetableDTO.setDescription(description);
        timetableDTO.setMaxAppointments(maxAppointments);
        timetableDTO.setLocation(location);
        timetableDTO.setCreatedAt(createdAt);
        timetableDTO.setUpdatedAt(updatedAt);
        return timetableDTO;
    }
}
