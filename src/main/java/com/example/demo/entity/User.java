package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name="last_name")
    private String lastName;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Boolean active;

    @Column
    private String role;
}
