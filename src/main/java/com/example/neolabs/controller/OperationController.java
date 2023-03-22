package com.example.neolabs.controller;

import com.example.neolabs.service.impl.OperationServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(name = "/api/v1/operations")
@Tag(name = "Operation Resource", description = "The Operation API ")
public class OperationController {

    private final OperationServiceImpl operationService;
    // TODO: 12.03.2023
}
