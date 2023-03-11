package com.example.neolabs.entity.operation;

import com.example.neolabs.entity.Group;
import com.example.neolabs.entity.User;
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
@Table(name = "group_operations")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupOperation extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "group_id",
            referencedColumnName = "id")
    Group group;

    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "user_id",
            referencedColumnName = "id")
    User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type")
    OperationType operationType;

    String description;
}
