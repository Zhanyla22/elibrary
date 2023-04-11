package com.example.neolabs.repository;

import com.example.neolabs.entity.MonthlyBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MonthlyBillRepository extends JpaRepository<MonthlyBill, Long> {

    @Query("SELECT m FROM MonthlyBill m WHERE m.studentGroupBill.id = ?1")
    List<MonthlyBill> findAllMonthlyBillsByStudentGroupBillID(Long studentGroupBillId);


}
