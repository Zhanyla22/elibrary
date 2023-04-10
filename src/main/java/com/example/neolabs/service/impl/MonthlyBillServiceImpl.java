package com.example.neolabs.service.impl;


import com.example.neolabs.dto.MonthlyBillDto;
import com.example.neolabs.entity.MonthlyBill;
import com.example.neolabs.entity.StudentGroupBill;
import com.example.neolabs.exception.ContentNotFoundException;
import com.example.neolabs.repository.MonthBillRepository;
import com.example.neolabs.service.MonthlyBillService;
import com.example.neolabs.service.StudentGroupBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MonthlyBillServiceImpl implements MonthlyBillService {

    final StudentGroupBillService studentGroupBillService;
    final MonthBillRepository monthlyBillRepository;
    @Override
    public MonthlyBill getMonthlyBillById(Long id) {
        return monthlyBillRepository.findById(id).orElseThrow(() -> {
            throw new ContentNotFoundException();
        });
    }

    @Override
    public List<MonthlyBillDto> getAllMonthlyBillsByStudentGroupBillId(Long studentGroupBillId) {
        return null;
    }

    @Override
    public MonthlyBillDto getMonthlyBillByMonthNumber(Long studentGroupBillId, Integer monthNumber) {
        return null;
    }

    @Override
    public List<MonthlyBillDto> createMonthlyBills(Long studentGroupBillId) {
        StudentGroupBill studentGroupBill = studentGroupBillService.getStudentGroupBillById(studentGroupBillId);

        MonthlyBill monthlyBill1 = new MonthlyBill(studentGroupBill,1,studentGroupBill.getGroup().getCourse().getCost()/3,
                studentGroupBill.getGroup().getStartDate().plusMonths(1));

        MonthlyBill monthlyBill2 = new MonthlyBill(studentGroupBill,2,studentGroupBill.getGroup().getCourse().getCost()/3,
                studentGroupBill.getGroup().getStartDate().plusMonths(2));

        MonthlyBill monthlyBill3 = new MonthlyBill(studentGroupBill,3,studentGroupBill.getGroup().getCourse().getCost()/3,
                studentGroupBill.getGroup().getEndDate());

        monthlyBillRepository.saveAll(List.of(monthlyBill1,monthlyBill2,monthlyBill3));
        return null;

}}
