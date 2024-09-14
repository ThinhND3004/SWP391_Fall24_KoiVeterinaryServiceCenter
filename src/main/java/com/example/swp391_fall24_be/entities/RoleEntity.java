package com.example.swp391_fall24_be.entities;

import jakarta.persistence.*;
<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
=======
import lombok.Data;
>>>>>>> 558470dbd353eac197b045e5f57e6ed11775cf52

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity (name = "roles")
<<<<<<< HEAD
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
=======
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

    @OneToMany(mappedBy = "role") // Sửa thành mappedBy đúng thuộc tính trong UserEntity
    private List<UserEntity> users;
}
