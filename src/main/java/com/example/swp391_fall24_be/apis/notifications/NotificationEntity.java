package com.example.swp391_fall24_be.apis.notifications;

import com.example.swp391_fall24_be.apis.accounts.AccountEntity;
import com.example.swp391_fall24_be.apis.bookings.BookingEntity;
import com.example.swp391_fall24_be.apis.notifications.dtos.NotificationDto;
import com.example.swp391_fall24_be.apis.services.ServiceEntity;
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
public class NotificationEntity implements IObject<NotificationDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "account_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private AccountEntity account;

    @JoinColumn(name = "sender_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private AccountEntity sender;

    @JoinColumn(name = "booking_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private BookingEntity booking;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "NVARCHAR(255)", nullable = false, updatable = false)
    private String description;

    @Column(name = "type", nullable = false)
    private NotificationTypeEnum type;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "read_at")
    private LocalDateTime readAt;



    @Override
    public NotificationDto toResponseDto() {
        NotificationDto dto = new NotificationDto();
        dto.setId(id);
        if (account != null) {
            dto.setAccountEmail(account.getEmail());
            dto.setAccountFullName(account.getFirstName() + " " + account.getLastName());
        }

        if(sender != null){
            dto.setSenderEmail(sender.getEmail());
            dto.setSenderFullName(sender.getFirstName() + " " + sender.getLastName());
        }

        if(booking != null) {

            dto.setBookingId(booking.getId());
        }
        dto.setTitle(title);
        dto.setDescription(description);
        dto.setType(type);
        dto.setReadAt(readAt);
        dto.setCreatedAt(createdAt);

        return dto;
    }
}
