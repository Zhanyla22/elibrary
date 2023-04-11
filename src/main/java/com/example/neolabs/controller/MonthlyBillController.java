package com.example.neolabs.controller;


import com.example.neolabs.controller.base.BaseController;
import com.example.neolabs.dto.MonthlyBillDto;
import com.example.neolabs.dto.PaymentDto;
import com.example.neolabs.service.MonthlyBillService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/monthly-bill")
public class MonthlyBillController extends BaseController {

    final MonthlyBillService monthlyBillService;

    @Operation(summary = "Create new monthly bills with student group bill id")
    @PostMapping("create/{studentGroupBillId}")
    public ResponseEntity<String> createMonthlyBills(@PathVariable("studentGroupBillId") Long studentGroupBillId){
        return ResponseEntity.ok(monthlyBillService.createMonthlyBills(studentGroupBillId));
    }

    @Operation(summary = "Get all monthly bills with student group bill id")
    @GetMapping("{studentGroupBillId}")
    public ResponseEntity<List<MonthlyBillDto>> getMonthlyBillsByStudentGroupBillId(@PathVariable("studentGroupBillId") Long studentGroupBillId){
        return ResponseEntity.ok(monthlyBillService.getAllMonthlyBillsByStudentGroupBillId(studentGroupBillId));
    }
}
