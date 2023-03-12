package com.example.neolabs.entity.operation;

import com.example.neolabs.entity.Mentor;
import com.example.neolabs.entity.User;
import com.example.neolabs.entity.base.BaseEntity;
import com.example.neolabs.enums.ApplicationStatus;
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
@Table(name = "mentor_operations")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MentorOperation extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "mentor_id",
            referencedColumnName = "id")
    Mentor mentor;

    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "user_id",
            referencedColumnName = "id")
    User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type")
    OperationType operationType;

    String description;
}
