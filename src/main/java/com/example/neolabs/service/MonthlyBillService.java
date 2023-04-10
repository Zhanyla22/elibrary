package com.example.neolabs.service;

import com.example.neolabs.dto.MonthlyBillDto;
import com.example.neolabs.entity.MonthlyBill;

import java.util.List;

public interface MonthlyBillService {
    MonthlyBill getMonthlyBillById(Long id);
    List<MonthlyBillDto> getAllMonthlyBillsByStudentGroupBillId(Long studentGroupBillId);
    MonthlyBillDto getMonthlyBillByMonthNumber(Long studentGroupBillId, Integer monthNumber);

    List<MonthlyBillDto> createMonthlyBills(Long studentGroupBillId);


}
