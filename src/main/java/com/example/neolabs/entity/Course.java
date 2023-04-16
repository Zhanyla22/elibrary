package com.example.neolabs.entity;

import com.example.neolabs.entity.base.BaseEntity;
import com.example.neolabs.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
@Table(name = "courses")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course extends BaseEntity {

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    Integer cost;

    @Column(name = "duration_in_month", nullable = false)
    Integer durationInMonth;

    @Enumerated(EnumType.STRING)
    Status status;

    @Column(name = "is_archived")
    Boolean isArchived;

    @Column(name = "archive_date")
    LocalDateTime archiveDate;

    @Column(name = "number_of_lessons")
    Integer numberOfLessons;

    @Column(name = "image_url")
    String imageUrl;

    String reason;

    @OneToMany(mappedBy = "course")
    List<Group> groups;

    @OneToMany(mappedBy = "course")
    List<Mentor> mentors;

    public List<Group> getGroups(){
        return groups != null ? groups : new ArrayList<>();
    }

    public List<Mentor> getMentors(){
        return mentors != null ? mentors : new ArrayList<>();
    }
}