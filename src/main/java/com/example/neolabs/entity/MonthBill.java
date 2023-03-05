package com.example.neolabs.entity;

import com.example.neolabs.entity.base.BaseEntity;
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
@Table(name = "month_bills")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MonthBill extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "student_group_bill_id",
            referencedColumnName = "id")
    StudentGroupBill studentGroupBill;

    @Column(name = "month_number")
    Integer monthNumber;

    Double debt;

    LocalDate deadline;
}
