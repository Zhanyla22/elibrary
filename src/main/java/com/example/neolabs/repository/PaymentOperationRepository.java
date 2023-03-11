package com.example.neolabs.repository;

import com.example.neolabs.entity.PaymentOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentOperationRepository extends JpaRepository<PaymentOperation, Long> {
}
