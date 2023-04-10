package com.example.neolabs.entity;

import com.example.neolabs.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "month_bills")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MonthlyBill extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "student_group_bill_id",
            referencedColumnName = "id")
    StudentGroupBill studentGroupBill;

    @Column(name = "month_number")
    Integer monthNumber;

    @Column(name = "monthly_debt")
    Double monthlyDebt;

    @Column(name = "monthly_deadline")
    LocalDate monthlyDeadline;
}
