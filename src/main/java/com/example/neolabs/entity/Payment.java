package com.example.neolabs.entity;

import com.example.neolabs.entity.base.BaseEntity;
import com.example.neolabs.enums.TransactionType;
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
@Table(name = "payments")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "student_id",
            referencedColumnName = "id")
    Student student;

    Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    TransactionType transactionType;

    @Column(name = "payment_date")
    LocalDateTime paymentDate;

    @Column(name = "total_debt")
    Double totalDebt;

    @Column(name = "total_payment_amount")
    Double totalPayment;
}
