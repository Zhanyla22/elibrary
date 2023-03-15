package com.example.neolabs.entity;

import com.example.neolabs.entity.base.BaseEntity;
import com.example.neolabs.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "groups")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Group extends BaseEntity {

    @Column(name = "name")
    String name;

    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "cource_id",
            referencedColumnName = "id")
    Course course;

    @Column(name = "max_capacity")
    Integer maxCapacity;

    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "mentor_id",
            referencedColumnName = "id")
    Mentor mentor;

    @Column(name = "start_date")
    LocalDate startDate;

    @Column(name = "end_date")
    LocalDate endDate;

    @Enumerated(EnumType.STRING)
    Status status;

    @Column(name = "is_archived")
    Boolean isArchived;

    @ManyToMany(mappedBy = "groups")
    Set<Student> students;
}
