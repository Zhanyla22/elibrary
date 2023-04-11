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

    Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    TransactionType transactionType;

    @Column(name = "payment_date")
    LocalDateTime paymentDate;

    @ManyToOne(optional = false)
    @JoinColumn(columnDefinition = "month_bill_id",
            referencedColumnName = "id")
    MonthlyBill monthlyBill;
}
