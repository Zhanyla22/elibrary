package com.example.neolabs.controller;

import com.example.neolabs.service.impl.OperationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/api/v1/operations")
@RequiredArgsConstructor
public class OperationController {
    private final OperationServiceImpl operationService;
    // TODO: 12.03.2023
}
