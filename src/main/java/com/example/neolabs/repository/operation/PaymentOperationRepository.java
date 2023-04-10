package com.example.neolabs.repository.operation;

import com.example.neolabs.entity.operation.PaymentOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentOperationRepository extends JpaRepository<PaymentOperation, Long> {
}
