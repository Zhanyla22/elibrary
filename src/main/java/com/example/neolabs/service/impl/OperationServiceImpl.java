package com.example.neolabs.service.impl;

import com.example.neolabs.dto.OperationDto;
import com.example.neolabs.entity.Application;
import com.example.neolabs.entity.User;
import com.example.neolabs.entity.operation.*;
import com.example.neolabs.enums.EntityEnum;
import com.example.neolabs.enums.OperationType;
import com.example.neolabs.mapper.OperationMapper;
import com.example.neolabs.repository.*;
import com.example.neolabs.service.OperationService;
import com.example.neolabs.util.OperationUtil;
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
    final OperationUtil operationUtil;

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
    public void recordApplicationOperation(Application application, OperationType operationType) {
        ApplicationOperation operation = ApplicationOperation.builder()
                .user(getCurrentUserEntity())
                .application(application)
                .operationType(operationType)
                .description(operationUtil.buildDescription(operationType, EntityEnum.APPLICATION, application.getId()))
                .build();
        applicationOpRepo.save(operation);
    }


    @Override
    public List<OperationDto> getAllOperations(boolean includeApplications, boolean includeStudents,
                                               boolean includeGroups, boolean includeCourses,
                                               boolean includeUsers, boolean includeMentors,
                                               boolean includeDepartments, boolean includePayments) {
        // TODO: 31.03.2023 add startDate and endDate
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
    }

    private User getCurrentUserEntity() {
        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(RuntimeException::new);
    }
}
