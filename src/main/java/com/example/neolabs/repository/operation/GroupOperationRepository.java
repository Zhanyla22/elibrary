package com.example.neolabs.repository.operation;

import com.example.neolabs.entity.User;
import com.example.neolabs.entity.operation.ApplicationOperation;
import com.example.neolabs.entity.operation.CourseOperation;
import com.example.neolabs.entity.operation.GroupOperation;
import com.example.neolabs.enums.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupOperationRepository extends JpaRepository<GroupOperation, Long> {
    List<GroupOperation> findAllByUser(User user);
    List<GroupOperation> findAllByOperationType(OperationType operationType);
    List<GroupOperation> findAllByUserAndOperationType(User user, OperationType operationType);
}
