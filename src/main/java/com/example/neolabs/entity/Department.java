package com.example.neolabs.entity;

import com.example.neolabs.entity.base.BaseEntity;
import com.example.neolabs.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "departments")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Department extends BaseEntity {

    @Column(nullable = false)
    String name;

    @Enumerated(EnumType.STRING)
    Status status;
}