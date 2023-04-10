package com.example.neolabs.service;

import com.example.neolabs.dto.OperationDto;
import com.example.neolabs.entity.*;
import com.example.neolabs.enums.OperationType;

import java.util.List;

public interface OperationService {

    void recordApplicationOperation(Application application, OperationType operationType);
    void recordStudentOperation(Student student, OperationType operationType);
    void recordGroupOperation(Group group, OperationType operationType);
    void recordCourseOperation(Course course, OperationType operationType);
    void recordUserOperation(User user, OperationType operationType);
    void recordMentorOperation(Mentor mentor, OperationType operationType);
    void recordDepartmentOperation(Department department, OperationType operationType);
    void recordPaymentOperation(Payment payment, OperationType operationType);
    void recordEnrollmentOperation(Student student, Long groupId);

    List<OperationDto> getAllOperations(boolean includeApplications,
                                        boolean includeStudents,
                                        boolean includeGroups,
                                        boolean includeCourses,
                                        boolean includeUsers,
                                        boolean includeMentors,
                                        boolean includeDepartments,
                                        boolean includePayments);
}
