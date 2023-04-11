package com.example.neolabs.service;

import com.example.neolabs.dto.CreateStudentGroupBillDto;
import com.example.neolabs.dto.StudentGroupBillDto;
import com.example.neolabs.entity.StudentGroupBill;

public interface StudentGroupBillService {

    StudentGroupBillDto getStudentGroupBill(Long studentId, Long groupId);

    StudentGroupBill getStudentGroupBillById(Long id);
    
    StudentGroupBillDto createStudentGroupBill(CreateStudentGroupBillDto createStudentGroupBillDto);

    StudentGroupBillDto getStudentGroupBillDtoById(Long studentGroupBillId);
}
