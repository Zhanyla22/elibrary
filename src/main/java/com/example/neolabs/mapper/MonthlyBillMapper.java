package com.example.neolabs.mapper;

import com.example.neolabs.dto.MonthlyBillDto;
import com.example.neolabs.entity.MonthlyBill;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MonthlyBillMapper {

    final StudentGroupBillMapper studentGroupBillMapper;

    public MonthlyBillDto entityToDto(MonthlyBill monthlyBill){
        return MonthlyBillDto.builder()
                .id(monthlyBill.getId())
                .studentGroupBillId(studentGroupBillMapper.entityToDto(monthlyBill.getStudentGroupBill()).getId())
                .monthlyDept(monthlyBill.getMonthlyDebt())
                .monthlyDeadline(monthlyBill.getMonthlyDeadline())
                .monthNumber(monthlyBill.getMonthNumber())
                .build();
    }

    public List<MonthlyBillDto> entityListToDtoList(List<MonthlyBill> monthlyBills) {
        return monthlyBills.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
