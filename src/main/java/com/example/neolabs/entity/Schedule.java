package com.example.neolabs.entity;

import com.example.neolabs.entity.base.BaseEntity;
import com.example.neolabs.enums.DayOfTheWeek;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schedules")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Schedule extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "group_id",
            referencedColumnName = "id")
    Group group;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_the_week")
    DayOfTheWeek dayOfTheWeek;

    @Column(name = "start_time")
    LocalTime startTime;

    @Column(name = "end_time")
    LocalTime endTime;

}
