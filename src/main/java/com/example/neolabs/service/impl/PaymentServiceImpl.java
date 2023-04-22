package com.example.neolabs.service.impl;

import com.example.neolabs.dto.PaymentDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.request.create.MakePaymentRequest;
import com.example.neolabs.entity.Payment;
import com.example.neolabs.entity.Student;
import com.example.neolabs.enums.EntityEnum;
import com.example.neolabs.exception.ContentNotFoundException;
import com.example.neolabs.exception.EntityNotFoundException;
import com.example.neolabs.mapper.PaymentMapper;
import com.example.neolabs.repository.PaymentRepository;
import com.example.neolabs.service.PaymentService;
import com.example.neolabs.util.DateUtil;
import com.example.neolabs.util.ResponseUtil;
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

    final StudentServiceImpl studentService;
    @Override
    public ResponseDto pay(MakePaymentRequest paymentRequest) {
        Student student = studentService.getStudentEntityById(paymentRequest.getStudentId());
        Payment payment =  paymentRepository.findPaymentByStudentId(student.getId());
        payment.setStudent(student);
        payment.setAmount(paymentRequest.getAmount());
        payment.setTransactionType(paymentRequest.getTransactionType());
        payment.setTotalPayment(payment.getTotalPayment() + paymentRequest.getAmount());
        payment.setTotalDebt(payment.getTotalDebt() - paymentRequest.getAmount());
        payment.setPaymentDate(LocalDateTime.now(DateUtil.getZoneId()));
        paymentRepository.save(payment);

        return ResponseUtil.buildSuccessResponse("Payment is done successfully");
    }

    @Override
    public PaymentDto getPaymentById(Long id){
        return PaymentMapper.paymentToPaymentDto(getPaymentEntityById(id));
    }


    @Override
    public Payment getPaymentEntityById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(EntityEnum.PAYMENT, "id", id);
        });
    }
}
