package com.example.neolabs.service.impl;

import com.example.neolabs.dto.OperationDto;
import com.example.neolabs.entity.Application;
import com.example.neolabs.entity.User;
import com.example.neolabs.entity.operation.*;
import com.example.neolabs.enums.OperationType;
import com.example.neolabs.mapper.OperationMapper;
import com.example.neolabs.repository.*;
import com.example.neolabs.service.OperationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OperationServiceImpl implements OperationService {

    final OperationMapper operationMapper;

    final ApplicationOperationRepository applicationOpRepo;
    final UserOperationRepository userOpRepo;
    final StudentOperationRepository studentOpRepo;
    final GroupOperationRepository groupOpRepo;
    final CourseOperationRepository courseOpRepo;
    final DepartmentOperationRepository departmentOpRepo;
    final MentorOperationRepository mentorOpRepo;
    final PaymentOperationRepository paymentOpRepo;

    final UserRepository userRepository;

    @Override
    public void recordApplicationOperation(Application application, OperationType operationType, String description) {
        ApplicationOperation operation = ApplicationOperation.builder()
                .user(getCurrentUserEntity())
                .application(application)
                .operationType(operationType)
                .description(description)
                .build();
        applicationOpRepo.save(operation);
    }

    @Override
    public List<OperationDto> getAllOperations(boolean includeApplications, boolean includeStudents,
                                               boolean includeGroups, boolean includeCourses,
                                               boolean includeUsers, boolean includeMentors,
                                               boolean includeDepartments, boolean includePayments) {
        List<ApplicationOperation> applicationOperations = includeApplications ? applicationOpRepo.findAll() : null;
        List<StudentOperation> studentOperations = includeStudents ? studentOpRepo.findAll() : null;
        List<GroupOperation> groupOperations = includeGroups ? groupOpRepo.findAll() : null;
        List<CourseOperation> courseOperations = includeCourses ? courseOpRepo.findAll() : null;
        List<UserOperation> userOperations = includeUsers ? userOpRepo.findAll() : null;
        List<MentorOperation> mentorOperations = includeMentors ? mentorOpRepo.findAll() : null;
        List<DepartmentOperation> departmentOperations = includeDepartments ? departmentOpRepo.findAll() : null;
        List<PaymentOperation> paymentOperations = includePayments ? paymentOpRepo.findAll() : null;
        return operationMapper.allOperationListToDtoList(applicationOperations, userOperations, groupOperations,
                studentOperations, departmentOperations, courseOperations, mentorOperations, paymentOperations);

        // TODO: 29.03.2023 this cant be the right approach
        //      I guess we will need to use the other approach for the performance purposes,
        //      otherwise this function will do 8 different queries everytime its called.
        //      Also, to sort the result of multiple operations, we will need to implement some sorting algorithm.
        //      -
        //      Although, the other approach is not correct table-relational wise, I guess we will need to do that
        //      for the sake of simplicity.
        //      -
        //      -
        //      -
        //      - ............. Or maybe its alright?
        //      -
        //      - S.M.

    }

    private User getCurrentUserEntity() {
        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(RuntimeException::new);
    }
}
