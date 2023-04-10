package com.example.neolabs.repository;

import com.example.neolabs.entity.MonthlyBill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthBillRepository extends JpaRepository<MonthlyBill, Long> {
}
