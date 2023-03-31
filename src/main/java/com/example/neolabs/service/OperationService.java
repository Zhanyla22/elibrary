package com.example.neolabs.service;

import com.example.neolabs.dto.OperationDto;
import com.example.neolabs.entity.Application;
import com.example.neolabs.enums.OperationType;

import java.util.List;

public interface OperationService {

    void recordApplicationOperation(Application application, OperationType operationType);

    List<OperationDto> getAllOperations(boolean includeApplications,
                                        boolean includeStudents,
                                        boolean includeGroups,
                                        boolean includeCourses,
                                        boolean includeUsers,
                                        boolean includeMentors,
                                        boolean includeDepartments,
                                        boolean includePayments);
}
