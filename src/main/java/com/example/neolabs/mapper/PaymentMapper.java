package com.example.neolabs.mapper;


import com.example.neolabs.dto.PaymentDto;
import com.example.neolabs.dto.request.create.MakePaymentRequest;
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

    public static PaymentDto paymentToPaymentDto(Payment payment){
        return PaymentDto.builder()
                .id(payment.getId())
                .studentId(payment.getStudent().getId())
                .amount(payment.getAmount())
                .transactionType(payment.getTransactionType())
                .totalPayment(payment.getTotalPayment())
                .totalDebt(payment.getTotalDebt())
                .build();
    }

    public MakePaymentRequest paymentToMakePaymentDto(Payment payment){
        return MakePaymentRequest.builder()
                .amount(payment.getAmount())
                .transactionType(payment.getTransactionType())
                .build();
    }

    public List<PaymentDto> entityListToDtoList(List<Payment> payment) {
        return payment.stream().map(PaymentMapper::paymentToPaymentDto).collect(Collectors.toList());
    }
}
