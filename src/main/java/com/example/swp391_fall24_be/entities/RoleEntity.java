package com.example.swp391_fall24_be.entities;

import com.example.swp391_fall24_be.apis.accounts.Account;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity (name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    private List<Account> accountList;
}
