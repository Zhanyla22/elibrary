package com.example.neolabs.repository.operation;

import com.example.neolabs.entity.User;
import com.example.neolabs.entity.operation.ApplicationOperation;
import com.example.neolabs.entity.operation.CourseOperation;
import com.example.neolabs.entity.operation.GroupOperation;
import com.example.neolabs.entity.operation.PaymentOperation;
import com.example.neolabs.enums.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentOperationRepository extends JpaRepository<PaymentOperation, Long> {
    List<PaymentOperation> findAllByUser(User user);
    List<PaymentOperation> findAllByOperationType(OperationType operationType);
    List<PaymentOperation> findAllByUserAndOperationType(User user, OperationType operationType);
}
