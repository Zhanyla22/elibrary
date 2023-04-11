package com.example.neolabs.mapper;


import com.example.neolabs.dto.PaymentDto;
import com.example.neolabs.entity.Payment;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentMapper {

    final MonthlyBillMapper monthlyBillMapper;

    public PaymentDto entityToDto(Payment payment){
        return PaymentDto.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .transactionType(payment.getTransactionType())
                .monthlyBillID(monthlyBillMapper.entityToDto(payment.getMonthlyBill()).getId())
                .build();
    }

    public List<PaymentDto> entityListToDtoList(List<Payment> payment) {
        return payment.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
