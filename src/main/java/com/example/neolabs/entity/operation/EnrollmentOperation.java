package com.example.neolabs.entity.operation;


import com.example.neolabs.entity.Student;
import com.example.neolabs.entity.User;
import com.example.neolabs.entity.base.BaseEntity;
import com.example.neolabs.enums.OperationType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "enrollment_operations")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EnrollmentOperation extends BaseEntity {
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
}
