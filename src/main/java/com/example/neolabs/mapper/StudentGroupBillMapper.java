package com.example.neolabs.mapper;


import com.example.neolabs.dto.StudentGroupBillDto;
import com.example.neolabs.entity.StudentGroupBill;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentGroupBillMapper {

    public StudentGroupBillDto entityToDto(StudentGroupBill studentGroupBill){
        return StudentGroupBillDto.builder()
                .id(studentGroupBill.getId())
                .groupId(GroupMapper.entityToDto(studentGroupBill.getGroup()).getId())
                .studentId(StudentMapper.entityToDto(studentGroupBill.getStudent()).getId())
                .studentGroupDebt(studentGroupBill.getStudentGroupDebt())
                .build();
    }

    public List<StudentGroupBillDto> entityListToDtoList(List<StudentGroupBill> studentGroupBills){
        return studentGroupBills.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
