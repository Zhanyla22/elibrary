package com.example.neolabs.entity;

import com.example.neolabs.entity.base.BaseEntity;
import com.example.neolabs.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subjects")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subject extends BaseEntity {

    @Column(name = "name", length = 2000)
    String name;

    @Enumerated(EnumType.STRING)
    Status status;
}
