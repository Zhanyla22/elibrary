package com.example.neolabs.entity;

import com.example.neolabs.entity.base.BaseEntity;
import com.example.neolabs.enums.Group;
import com.example.neolabs.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courses")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course extends BaseEntity {

    @Column(name = "name")
    String name;

    @Enumerated(EnumType.STRING)
    Group groupss;

    @Enumerated(EnumType.STRING)
    Status status;
}
