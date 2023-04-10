package com.example.neolabs.service.impl;

import com.example.neolabs.dto.PaymentDto;
import com.example.neolabs.entity.MonthlyBill;
import com.example.neolabs.entity.Payment;
import com.example.neolabs.entity.StudentGroupBill;
import com.example.neolabs.exception.ContentNotFoundException;
import com.example.neolabs.mapper.PaymentMapper;
import com.example.neolabs.repository.MonthBillRepository;
import com.example.neolabs.repository.PaymentRepository;
import com.example.neolabs.repository.StudentGroupBillRepository;
import com.example.neolabs.service.MonthlyBillService;
import com.example.neolabs.service.PaymentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentServiceImpl implements PaymentService {

    final PaymentRepository paymentRepository;
    final PaymentMapper paymentMapper;
    final MonthlyBillService monthlyBillService;
    final MonthBillRepository monthlyBillRepository;

    final StudentGroupBillRepository studentGroupBillRepository;

    @Override
    public PaymentDto pay(PaymentDto paymentDto) {
        MonthlyBill monthlyBill = monthlyBillService.getMonthlyBillById(paymentDto.getMonthlyBillID());
        StudentGroupBill studentGroupBill = monthlyBill.getStudentGroupBill();

        Payment payment = new Payment();
        payment.setAmount(paymentDto.getAmount());
        payment.setTransactionType(paymentDto.getTransactionType());
        payment.setMonthlyBill(monthlyBill);
        payment.setPaymentDate(LocalDateTime.now());

        Double newMonthlyDebt = monthlyBill.getMonthlyDebt() - paymentDto.getAmount();
        monthlyBill.setMonthlyDebt(newMonthlyDebt);
        monthlyBillRepository.save(monthlyBill);
        Double newStudentGroupDebt = studentGroupBill.getStudentGroupDebt() - paymentDto.getAmount();
        studentGroupBill.setStudentGroupDebt(newStudentGroupDebt);
        studentGroupBillRepository.save(studentGroupBill);

        return paymentMapper.entityToDto(paymentRepository.save(payment));
    }

    @Override
    public PaymentDto getPaymentById(Long id){
        Payment payment = isPaymentDeleted(id);
        return paymentMapper.entityToDto(payment);
    }

    @Override
    public Payment isPaymentDeleted(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> {
            throw new ContentNotFoundException();
        });
        if (payment.getDeletedDate() != null){
            throw new NotFoundException("Payment with id: " + id + " was deleted!");
        }
        return payment;
    }
}
