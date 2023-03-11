package com.example.neolabs.entity;

import com.example.neolabs.entity.base.BaseEntity;
import com.example.neolabs.enums.Room;
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
@Table(name = "schedule_units")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleUnit extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "schedule_id",
            referencedColumnName = "id")
    Schedule schedule;

    @Column(name = "start_timestamp")
    LocalTime startTimestamp;

    @Column(name = "end_timestamp")
    LocalTime endTimestamp;

    @Enumerated(EnumType.STRING)
    @Column(name = "room")
    Room room;
}
