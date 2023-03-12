package com.example.neolabs.service.impl;

import com.example.neolabs.entity.Application;
import com.example.neolabs.entity.User;
import com.example.neolabs.entity.operation.ApplicationOperation;
import com.example.neolabs.enums.OperationType;
import com.example.neolabs.repository.ApplicationOperationRepository;
import com.example.neolabs.repository.UserRepository;
import com.example.neolabs.service.OperationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OperationServiceImpl implements OperationService {
    final ApplicationOperationRepository appOpRepo;
    final UserRepository userRepository;

    @Override
    public void recordApplicationOperation(Application application, OperationType operationType, String description) {
        ApplicationOperation operation = ApplicationOperation.builder()
                .user(getCurrentUserEntity())
                .application(application)
                .operationType(operationType)
                .description(description)
                .build();
        appOpRepo.save(operation);
    }

    @Override
    public List<ApplicationOperation> getAllApplicationOperations() {
        return appOpRepo.findAll();
    }

    private User getCurrentUserEntity(){
        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(RuntimeException::new);
    }
}
