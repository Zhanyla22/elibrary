package com.example.neolabs.repository;

import com.example.neolabs.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT p FROM Payment p WHERE p.student.id = ?1")
    Payment findPaymentByStudentId(Long studentId);
}
