package com.example.neolabs.repository.operation;

import com.example.neolabs.entity.User;
import com.example.neolabs.entity.operation.ApplicationOperation;
import com.example.neolabs.entity.operation.CourseOperation;
import com.example.neolabs.enums.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseOperationRepository extends JpaRepository<CourseOperation, Long> {
    List<CourseOperation> findAllByUser(User user);
    List<CourseOperation> findAllByOperationType(OperationType operationType);
    List<CourseOperation> findAllByUserAndOperationType(User user, OperationType operationType);
}
