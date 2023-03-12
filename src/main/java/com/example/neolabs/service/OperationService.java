package com.example.neolabs.service;

import com.example.neolabs.dto.OperationDto;
import com.example.neolabs.entity.Application;
import com.example.neolabs.entity.operation.ApplicationOperation;
import com.example.neolabs.enums.OperationType;

import java.util.List;

public interface OperationService {
    void recordApplicationOperation(Application application, OperationType operationType, String description);
    List<ApplicationOperation> getAllApplicationOperations();
    //List<OperationDto> getAllOperations();
}
