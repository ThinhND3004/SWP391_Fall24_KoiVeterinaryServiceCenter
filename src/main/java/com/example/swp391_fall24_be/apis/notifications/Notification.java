package com.example.swp391_fall24_be.apis.notifications;

import com.example.swp391_fall24_be.apis.accounts.Account;
import com.example.swp391_fall24_be.apis.notifications.dtos.NotificationDto;
import com.example.swp391_fall24_be.core.IObject;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity(name = "notifications")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Notification implements IObject<NotificationDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn
    @ManyToOne
    private Account account;

    @Column(name = "description", nullable = false, updatable = false)
    private String description;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Override
    public NotificationDto toResponseDto() {
        NotificationDto dto = new NotificationDto();
        dto.setAccount(account);
        dto.setDescription(description);
        dto.setCreatedAt(createdAt);
        return null;
    }
}
