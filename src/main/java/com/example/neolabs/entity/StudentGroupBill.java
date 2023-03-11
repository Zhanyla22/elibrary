package com.example.neolabs.entity;

import com.example.neolabs.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student_group_bills")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentGroupBill extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "student_id",
            referencedColumnName = "id")
    Student student;

    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "group_id",
            referencedColumnName = "id")
    Group group;

    Double debt;
}
