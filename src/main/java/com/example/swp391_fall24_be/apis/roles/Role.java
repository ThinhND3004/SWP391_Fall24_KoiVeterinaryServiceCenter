package com.example.swp391_fall24_be.apis.roles;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity (name = "roles")
@Data
public class Role{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column()
    private String name;

    @Column (columnDefinition = "TEXT")
    private String description;

    @Column ()
    private LocalDateTime createAt;

    @Column ()
    private LocalDateTime updateAt;
}
