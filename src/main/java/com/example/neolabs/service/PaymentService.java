package com.example.neolabs.service;

import com.example.neolabs.dto.PaymentDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.request.create.MakePaymentRequest;
import com.example.neolabs.entity.Payment;

public interface PaymentService {

    ResponseDto pay(MakePaymentRequest makePaymentRequest);

    PaymentDto getPaymentById(Long id);
    Payment getPaymentEntityById(Long id);
}
