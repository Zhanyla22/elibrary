package com.example.neolabs.repository.operation;

import com.example.neolabs.entity.User;
import com.example.neolabs.entity.operation.ApplicationOperation;
import com.example.neolabs.entity.operation.CourseOperation;
import com.example.neolabs.entity.operation.GroupOperation;
import com.example.neolabs.entity.operation.UserOperation;
import com.example.neolabs.enums.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserOperationRepository extends JpaRepository<UserOperation, Long> {
    List<UserOperation> findAllByUser(User user);
    List<UserOperation> findAllByOperationType(OperationType operationType);
    List<UserOperation> findAllByUserAndOperationType(User user, OperationType operationType);
}
