package com.example.neolabs.entity;

import com.example.neolabs.entity.base.BaseEntity;
import com.example.neolabs.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    @JoinColumn(columnDefinition = "course_id",
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

    @Column(name = "image_url")
    String imageUrl;

    @ManyToMany(mappedBy = "groups")
    List<Student> students = new ArrayList<>();

    @Column(name = "reason_changed_status")
    String reason;

    @Column(name = "archive_date")
    LocalDateTime archiveDate;
}
