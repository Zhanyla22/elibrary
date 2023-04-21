package com.example.neolabs.controller;

import com.example.neolabs.dto.OperationDto;
import com.example.neolabs.enums.EntityEnum;
import com.example.neolabs.enums.OperationTarget;
import com.example.neolabs.enums.OperationType;
import com.example.neolabs.service.impl.OperationServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/operations")
@Tag(name = "Operation Resource", description = "The Operation API ")
public class OperationController {

    private final OperationServiceImpl operationService;

    @GetMapping("")
    public ResponseEntity<List<OperationDto>> getAllOperations(@RequestParam("userId") Optional<Long> userId,
                                                               @RequestParam("type") Optional<OperationType> type,
                                                               @RequestParam("target") Optional<OperationTarget> target){
        return ResponseEntity.ok(operationService.getAllOperations(userId.orElse(null), type.orElse(null),
                target.orElse(null)));
    }


}
