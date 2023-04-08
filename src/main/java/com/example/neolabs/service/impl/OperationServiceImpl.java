package com.example.neolabs.service.impl;

import com.example.neolabs.dto.OperationDto;
import com.example.neolabs.entity.*;
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
    public void recordStudentOperation(Student student, OperationType operationType) {
        StudentOperation operation = StudentOperation.builder()
                .user(getCurrentUserEntity())
                .student(student)
                .operationType(operationType)
                .description(operationUtil.buildDescription(operationType, EntityEnum.STUDENT, student.getId()))
                .build();
        studentOpRepo.save(operation);
    }

    @Override
    public void recordGroupOperation(Group group, OperationType operationType) {
        GroupOperation operation = GroupOperation.builder()
                .user(getCurrentUserEntity())
                .group(group)
                .operationType(operationType)
                .description(operationUtil.buildDescription(operationType, EntityEnum.GROUP, group.getId()))
                .build();
        groupOpRepo.save(operation);
    }

    @Override
    public void recordCourseOperation(Course course, OperationType operationType) {
        CourseOperation operation = CourseOperation.builder()
                .user(getCurrentUserEntity())
                .course(course)
                .operationType(operationType)
                .description(operationUtil.buildDescription(operationType, EntityEnum.COURSE, course.getId()))
                .build();
        courseOpRepo.save(operation);
    }

    @Override
    public void recordUserOperation(User user, OperationType operationType) {
        UserOperation operation = UserOperation.builder()
                .user(getCurrentUserEntity())
                .targetUser(user)
                .operationType(operationType)
                .description(operationUtil.buildDescription(operationType, EntityEnum.USER, user.getId()))
                .build();
        userOpRepo.save(operation);
    }

    @Override
    public void recordMentorOperation(Mentor mentor, OperationType operationType) {
        MentorOperation operation = MentorOperation.builder()
                .user(getCurrentUserEntity())
                .mentor(mentor)
                .operationType(operationType)
                .description(operationUtil.buildDescription(operationType, EntityEnum.MENTOR, mentor.getId()))
                .build();
        mentorOpRepo.save(operation);
    }

    @Override
    public void recordDepartmentOperation(Department department, OperationType operationType) {
        DepartmentOperation operation = DepartmentOperation.builder()
                .user(getCurrentUserEntity())
                .department(department)
                .operationType(operationType)
                .description(operationUtil.buildDescription(operationType, EntityEnum.DEPARTMENT, department.getId()))
                .build();
        departmentOpRepo.save(operation);
    }

    @Override
    public void recordPaymentOperation(Payment payment, OperationType operationType) {
        PaymentOperation operation = PaymentOperation.builder()
                .user(getCurrentUserEntity())
                .payment(payment)
                .operationType(operationType)
                .description(operationUtil.buildDescription(operationType, EntityEnum.PAYMENT, payment.getId()))
                .build();
        paymentOpRepo.save(operation);
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
