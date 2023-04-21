package com.example.neolabs.repository.operation;

import com.example.neolabs.entity.User;
import com.example.neolabs.entity.operation.ApplicationOperation;
import com.example.neolabs.entity.operation.CourseOperation;
import com.example.neolabs.entity.operation.GroupOperation;
import com.example.neolabs.entity.operation.StudentOperation;
import com.example.neolabs.enums.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentOperationRepository extends JpaRepository<StudentOperation, Long> {
    List<StudentOperation> findAllByUser(User user);
    List<StudentOperation> findAllByOperationType(OperationType operationType);
    List<StudentOperation> findAllByUserAndOperationType(User user, OperationType operationType);
}
