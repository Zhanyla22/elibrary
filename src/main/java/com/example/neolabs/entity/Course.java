package com.example.neolabs.entity;

import com.example.neolabs.entity.base.BaseEntity;
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

    @Column(nullable = false)
    String name;

    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "department_id",
            referencedColumnName = "id")
    Department department;

    @Column(nullable = false)
    Double cost;

    @Column(name = "duration_in_month", nullable = false)
    Integer durationInMonth;

    @Enumerated(EnumType.STRING)
    Status status;

    @Column(name = "is_archived")
    Boolean isArchived;

    @Column(name = "number_of_lessons")
    Integer numberOfLessons;
}