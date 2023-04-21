package com.example.neolabs.service;

import com.example.neolabs.dto.OperationDto;
import com.example.neolabs.entity.*;
import com.example.neolabs.enums.OperationTarget;
import com.example.neolabs.enums.OperationType;

import java.util.List;

public interface OperationService {

    void recordApplicationOperation(Application application, OperationType operationType);

    void recordStudentOperation(Student student, OperationType operationType);

    void recordGroupOperation(Group group, OperationType operationType);

    void recordCourseOperation(Course course, OperationType operationType);

    void recordUserOperation(User user, OperationType operationType);

    void recordMentorOperation(Mentor mentor, OperationType operationType);

    void recordPaymentOperation(Payment payment, OperationType operationType);

    void recordEnrollmentOperation(Group group, Long studentId);

    List<OperationDto> getAllOperations(Long userId, OperationType operationType, OperationTarget operationTarget);
}
