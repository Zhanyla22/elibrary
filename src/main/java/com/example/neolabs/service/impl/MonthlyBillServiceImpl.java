package com.example.neolabs.service.impl;


import com.example.neolabs.dto.MonthlyBillDto;
import com.example.neolabs.entity.MonthlyBill;
import com.example.neolabs.entity.StudentGroupBill;
import com.example.neolabs.enums.EntityEnum;
import com.example.neolabs.exception.BaseException;
import com.example.neolabs.exception.ContentNotFoundException;
import com.example.neolabs.exception.EntityNotFoundException;
import com.example.neolabs.mapper.MonthlyBillMapper;
import com.example.neolabs.repository.MonthlyBillRepository;
import com.example.neolabs.repository.StudentGroupBillRepository;
import com.example.neolabs.service.MonthlyBillService;
import com.example.neolabs.service.StudentGroupBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MonthlyBillServiceImpl implements MonthlyBillService {

    final StudentGroupBillService studentGroupBillService;
    final MonthlyBillRepository monthlyBillRepository;

    final MonthlyBillMapper monthlyBillMapper;

    final StudentGroupBillRepository studentGroupBillRepository;
    @Override
    public MonthlyBill getMonthlyBillById(Long id) {
        return monthlyBillRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(EntityEnum.MONTHLY_BILL, "id", id);
        });
    }

    @Override
    public List<MonthlyBillDto> getAllMonthlyBillsByStudentGroupBillId(Long studentGroupBillId) {
        studentGroupBillService.getStudentGroupBillById(studentGroupBillId);
        return monthlyBillMapper.entityListToDtoList(monthlyBillRepository.findAllMonthlyBillsByStudentGroupBillID(studentGroupBillId));
    }

    @Override
    public MonthlyBillDto getMonthlyBillByMonthNumber(Long studentGroupBillId, Integer monthNumber) {
        return null;
    }

    @Override
    public String createMonthlyBills(Long studentGroupBillId) {
        StudentGroupBill studentGroupBill = studentGroupBillService.getStudentGroupBillById(studentGroupBillId);
        if (getAllMonthlyBillsByStudentGroupBillId(studentGroupBillId) == null){

        Integer durationInMonths = studentGroupBill.getGroup().getCourse().getDurationInMonth();
        Double courseBill = studentGroupBill.getGroup().getCourse().getCost();
        LocalDate monthlyDeadline = studentGroupBill.getGroup().getStartDate();

        for (int i = 1; i <= durationInMonths; i++) {
            MonthlyBill monthlyBills = new MonthlyBill(studentGroupBill, i, courseBill / durationInMonths,
                    monthlyDeadline.plusMonths(i));
            monthlyBillRepository.save(monthlyBills);
        }}
        else {
            throw new BaseException("Monthly bills for student's group bill with id: " + studentGroupBillId + " have already been created", HttpStatus.METHOD_NOT_ALLOWED);
        }
        return "Monthly bills for student group bill with id: " + studentGroupBillId + " are successfully created!";

}}
