package com.example.neolabs.service.impl;

import com.example.neolabs.dto.PaymentDto;
import com.example.neolabs.entity.MonthlyBill;
import com.example.neolabs.entity.Payment;
import com.example.neolabs.entity.StudentGroupBill;
import com.example.neolabs.exception.BaseException;
import com.example.neolabs.exception.ContentNotFoundException;
import com.example.neolabs.mapper.PaymentMapper;
import com.example.neolabs.repository.MonthlyBillRepository;
import com.example.neolabs.repository.PaymentRepository;
import com.example.neolabs.repository.StudentGroupBillRepository;
import com.example.neolabs.service.MonthlyBillService;
import com.example.neolabs.service.PaymentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.prefs.BackingStoreException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentServiceImpl implements PaymentService {

    final PaymentRepository paymentRepository;
    final PaymentMapper paymentMapper;
    final MonthlyBillService monthlyBillService;
    final MonthlyBillRepository monthlyBillRepository;

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
        if (newMonthlyDebt > 0){
        monthlyBill.setMonthlyDebt(newMonthlyDebt);
        monthlyBillRepository.save(monthlyBill);}
        else {
            throw new BaseException("Debt can't be negative", HttpStatus.METHOD_NOT_ALLOWED);
        }

        Double newStudentGroupDebt = studentGroupBill.getStudentGroupDebt() - paymentDto.getAmount();
        if (newStudentGroupDebt > 0){
        studentGroupBill.setStudentGroupDebt(newStudentGroupDebt);
        studentGroupBillRepository.save(studentGroupBill);}
        else {
            throw new BaseException("Debt can't be negative", HttpStatus.METHOD_NOT_ALLOWED);
        }

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
