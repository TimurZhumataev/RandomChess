package org.example.randomchess.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="my_user")
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="role")
    private Role role;
}
