package com.example.neolabs.repository.operation;

import com.example.neolabs.entity.User;
import com.example.neolabs.entity.operation.ApplicationOperation;
import com.example.neolabs.enums.OperationType;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationOperationRepository extends JpaRepository<ApplicationOperation, Long> {
    List<ApplicationOperation> findAllByUser(User user);
    List<ApplicationOperation> findAllByOperationType(OperationType operationType);
    List<ApplicationOperation> findAllByUserAndOperationType(User user, OperationType operationType);
}
