package com.example.swp391_fall24_be.apis.gg_meeting.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateMeetDto {
    @NotBlank(message = "Started At is required!")
    private LocalDateTime startedAt;

    @NotBlank(message = "Started At is required!")
    private LocalDateTime endedAt;
}
