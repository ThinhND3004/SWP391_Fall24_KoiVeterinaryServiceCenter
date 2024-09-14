package com.example.swp391_fall24_be.apis.roles;

import com.example.swp391_fall24_be.apis.accounts.Account;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity (name = "roles")
@Data
public class Role{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column()
    private String name;

    @Column (columnDefinition = "TEXT")
    private String description;

    @Column ()
    private LocalDateTime createAt;

    @Column ()
    private LocalDateTime updateAt;

    @OneToMany
    private List<Account> accounts;
}
