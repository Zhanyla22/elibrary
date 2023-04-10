package com.example.neolabs.service;

import com.example.neolabs.dto.PaymentDto;
import com.example.neolabs.entity.Payment;

public interface PaymentService {

    PaymentDto pay(PaymentDto paymentDto);

    PaymentDto getPaymentById(Long id);
    Payment isPaymentDeleted(Long id);
}
