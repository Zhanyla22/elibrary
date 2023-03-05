package com.example.neolabs.entity;

import com.example.neolabs.entity.base.BaseEntity;
import com.example.neolabs.enums.OperationType;
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
@Table(name = "student_operations")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentOperation extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "student_id",
            referencedColumnName = "id")
    Student student;

    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "user_id",
            referencedColumnName = "id")
    User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type")
    OperationType operationType;

    String description;

    @Column(name = "operation_date_time")
    LocalDateTime operationDateTime;
}
