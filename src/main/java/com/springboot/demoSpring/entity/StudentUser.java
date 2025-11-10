package com.springboot.demoSpring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class StudentUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String Username;

    private String email;

    // optional: password, role
    private String password;
    private String role; // e.g. "ROLE_USER", "ROLE_ADMIN"

    public String getName() {
        return Username;
    }
}
