package com.example.neolabs.repository.operation;

import com.example.neolabs.entity.User;
import com.example.neolabs.entity.operation.ApplicationOperation;
import com.example.neolabs.entity.operation.CourseOperation;
import com.example.neolabs.entity.operation.GroupOperation;
import com.example.neolabs.entity.operation.MentorOperation;
import com.example.neolabs.enums.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentorOperationRepository extends JpaRepository<MentorOperation, Long> {
    List<MentorOperation> findAllByUser(User user);
    List<MentorOperation> findAllByOperationType(OperationType operationType);
    List<MentorOperation> findAllByUserAndOperationType(User user, OperationType operationType);
}
