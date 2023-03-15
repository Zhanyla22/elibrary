package com.example.neolabs.entity;

import com.example.neolabs.entity.base.BaseEntity;
import com.example.neolabs.enums.ApplicationStatus;
import com.example.neolabs.enums.Education;
import com.example.neolabs.enums.Gender;
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
@Table(name = "applications")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Application extends BaseEntity {

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    String email;

    @Column(name = "phone_number")
    String phoneNumber;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column(name = "has_laptop")
    Boolean hasLaptop;

    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "cource_id",
            referencedColumnName = "id")
    private Course course;

    @ManyToOne
    @JoinColumn(columnDefinition = "marketing_strategy_id",
            referencedColumnName = "id")
    MarketingStrategy marketingStrategy;

    @Column(name = "is_archived")
    Boolean isArchived;

    @Column(name = "archive_reason")
    String archiveReason;

    @Column(length = 1000)
    String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "application_status")
    ApplicationStatus applicationStatus;

    @Column(name = "application_status_update_time")
    LocalDateTime applicationStatusUpdateDate;

    @Enumerated(EnumType.STRING)
    Education education;
}
