package com.example.neolabs.controller;

import com.example.neolabs.controller.base.BaseController;
import com.example.neolabs.dto.CreateStudentGroupBillDto;
import com.example.neolabs.dto.PaymentDto;
import com.example.neolabs.dto.StudentGroupBillDto;
import com.example.neolabs.service.StudentGroupBillService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/student-group-bill")
@RequiredArgsConstructor
public class StudentGroupBillController extends BaseController {

    final StudentGroupBillService studentGroupBillService;

    @Operation(summary = "Create a student's group bill")
    @PostMapping("/create")
    public ResponseEntity<StudentGroupBillDto> makeSinglePayment(@RequestBody CreateStudentGroupBillDto createStudentGroupBillDto){
        return ResponseEntity.ok(studentGroupBillService.createStudentGroupBill(createStudentGroupBillDto));
    }

    @Operation(summary = "Get a payment by Id")
    @GetMapping("{studentGroupBillId}")
    public ResponseEntity<StudentGroupBillDto> getPaymentById(@PathVariable("studentGroupBillId") Long studentGroupBillId){
        return ResponseEntity.ok(studentGroupBillService.getStudentGroupBillDtoById(studentGroupBillId));
    }
}
