package com.example.neolabs.entity;

import com.example.neolabs.entity.base.BaseEntity;
import com.example.neolabs.enums.Gender;
import com.example.neolabs.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student extends BaseEntity {
    String email;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "phone_number")
    String phoneNumber;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @Enumerated(EnumType.STRING)
    Status status;

    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "application_id",
            referencedColumnName = "id")
    Application application;

    @Column(name = "is_archived")
    Boolean isArchived;

    @Column(name = "archive_reason")
    String archiveReason;

    @Column(name = "total_debt")
    Integer totalDebt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "students_groups",
            joinColumns = { @JoinColumn(name = "student_id") },
            inverseJoinColumns = { @JoinColumn(name = "group_id") })
    List<Group> groups;

    @OneToMany(mappedBy = "student")
    List<StudentGroupBill> groupBills;
}
