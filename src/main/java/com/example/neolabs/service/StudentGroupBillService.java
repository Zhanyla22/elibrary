package com.example.neolabs.service;

import com.example.neolabs.dto.request.create.CreateStudentGroupBillRequest;
import com.example.neolabs.dto.StudentGroupBillDto;
import com.example.neolabs.entity.StudentGroupBill;

public interface StudentGroupBillService {

    StudentGroupBillDto getStudentGroupBill(Long studentId, Long groupId);

    StudentGroupBill getStudentGroupBillById(Long id);
    
    StudentGroupBillDto createStudentGroupBill(CreateStudentGroupBillRequest createStudentGroupBillRequest);

    StudentGroupBillDto getStudentGroupBillDtoById(Long studentGroupBillId);
}
