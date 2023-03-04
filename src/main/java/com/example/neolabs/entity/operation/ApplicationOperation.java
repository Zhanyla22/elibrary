package com.example.neolabs.entity.operation;

import com.example.neolabs.entity.Application;
import com.example.neolabs.entity.User;
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
@Table(name = "application_operations")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationOperation {

    @OneToMany
    @JoinColumn(columnDefinition = "application_id",
                referencedColumnName = "id")
    Application application;

    @OneToMany
    @JoinColumn(columnDefinition = "user_id",
                referencedColumnName = "id")
    User user;

    @Column(length = 500)
    String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type")
    OperationType operationType;
}
