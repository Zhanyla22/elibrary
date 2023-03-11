package com.example.neolabs.entity;

import com.example.neolabs.entity.base.BaseEntity;
import com.example.neolabs.enums.Status;
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
@Table(name = "groups")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Group extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "cource_id",
            referencedColumnName = "id")
    private Course cource;

    @Column(name = "max_capacity")
    private Integer maxCapacity;

    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "mentor_id",
            referencedColumnName = "id")
    private Mentor mentor;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "is_archived")
    private Boolean isArchived;
}
