package com.example.neolabs.service.impl;

import com.example.neolabs.dto.StudentGroupBillDto;
import com.example.neolabs.entity.StudentGroupBill;
import com.example.neolabs.exception.ContentNotFoundException;
import com.example.neolabs.repository.StudentGroupBillRepository;
import com.example.neolabs.service.StudentGroupBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class StudentGroupBillServiceImpl implements StudentGroupBillService {

    final StudentGroupBillRepository studentGroupBillRepository;

    @Override
    public StudentGroupBillDto getStudentGroupBill(Long studentId, Long groupId) {
        return null;
    }

    @Override
    public StudentGroupBill getStudentGroupBillById(Long id) {
        return studentGroupBillRepository.findById(id).orElseThrow(() -> {
            throw new ContentNotFoundException();
        });
    }
}
