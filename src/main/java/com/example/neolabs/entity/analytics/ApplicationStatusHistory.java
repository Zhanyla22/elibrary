package com.example.neolabs.entity.analytics;

import com.example.neolabs.entity.Application;
import com.example.neolabs.entity.base.BaseEntity;
import com.example.neolabs.enums.ApplicationStatus;
import com.example.neolabs.enums.LeavingReason;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "application_status_history")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationStatusHistory extends BaseEntity {
    @OneToOne
    @JoinColumn(columnDefinition = "application_id",
            referencedColumnName = "id")
    Application application;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_before_archiving")
    ApplicationStatus statusBeforeArchiving;

    @Column(name = "application_creation_date")
    LocalDate applicationCreationDate; //not best practice, but I had to do it

    @Column(name = "is_converted")
    Boolean isConverted;

    @Enumerated(EnumType.STRING)
    @Column(name = "leaving_reason")
    LeavingReason leavingReason;
}
