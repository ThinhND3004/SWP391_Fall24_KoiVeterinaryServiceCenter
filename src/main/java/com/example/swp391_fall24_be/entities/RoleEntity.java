package com.example.swp391_fall24_be.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

<<<<<<< HEAD
@Entity (name = "role")
=======
@Entity (name = "roles")
@Data
>>>>>>> 558470dbd353eac197b045e5f57e6ed11775cf52
public class RoleEntity {
    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column (name = "description")
    private String description;

    @Column (name = "create_at")
    private LocalDateTime createAt;

    @Column (name = "update_at")
    private LocalDateTime updateAt;

    @OneToMany
    private List<UserEntity> users;


}
