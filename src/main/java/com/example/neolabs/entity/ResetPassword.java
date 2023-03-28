package com.example.neolabs.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reset_password")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResetPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "code")
    String code;

    @Column(name = "date_expiration")
    LocalDateTime dateExpiration;

    @ManyToOne
    User user;

    @Column(name = "is_active")
    Boolean isActive;
}
