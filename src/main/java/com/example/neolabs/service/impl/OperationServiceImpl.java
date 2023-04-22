package com.example.neolabs.service.impl;

import com.example.neolabs.dto.OperationDto;
import com.example.neolabs.entity.*;
import com.example.neolabs.entity.operation.*;
import com.example.neolabs.enums.EntityEnum;
import com.example.neolabs.enums.OperationTarget;
import com.example.neolabs.enums.OperationType;
import com.example.neolabs.exception.EntityNotFoundException;
import com.example.neolabs.mapper.OperationMapper;
import com.example.neolabs.repository.UserRepository;
import com.example.neolabs.repository.operation.*;
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

    final OperationUtil operationUtil;

    final ApplicationOperationRepository applicationOpRepo;
    final UserOperationRepository userOpRepo;
    final StudentOperationRepository studentOpRepo;
    final GroupOperationRepository groupOpRepo;
    final CourseOperationRepository courseOpRepo;
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
    public void recordEnrollmentOperation(Group group, Long studentId) {
        CourseOperation operation = CourseOperation.builder()
                .user(getCurrentUserEntity())
                .course(group.getCourse())
                .description(operationUtil.buildEnrollDescription(group.getId(), studentId))
                .operationType(OperationType.ENROLLMENT)
                .build();
        courseOpRepo.save(operation);
    }

    @Override
    public List<OperationDto> getAllOperations(Long userId, OperationType operationType, OperationTarget target) {
        if (userId == null && operationType == null && target == null) {
            return findAllOperations(); //findAllOperationsByTypeAndUserId(null, null) also works
        }
        if (userId == null && operationType == null) {
            return findAllOperationsByTargetAndTypeAndUserId(target, null, null);
        }
        if (userId == null && target == null) {
            return findAllOperationsByType(operationType);
        }
        if (userId == null) {
            return findAllOperationsByTargetAndTypeAndUserId(target, operationType, null);
        }
        if (operationType == null && target == null) {
            return findAllOperationsByUserId(userId);
        }
        if (operationType == null) {
            return findAllOperationsByTargetAndTypeAndUserId(target, null, userId);
        }
        if (target == null) {
            return findAllOperationsByTypeAndUserId(operationType, userId);
        }
        return findAllOperationsByTargetAndTypeAndUserId(target, operationType, userId);
    }

    private List<OperationDto> findAllOperations(){
        List<ApplicationOperation> applicationOperations = applicationOpRepo.findAll();
        List<CourseOperation> courseOperations = courseOpRepo.findAll();
        List<GroupOperation> groupOperations = groupOpRepo.findAll();
        List<MentorOperation> mentorOperations = mentorOpRepo.findAll();
        List<PaymentOperation> paymentOperations = paymentOpRepo.findAll();
        List<StudentOperation> studentOperations = studentOpRepo.findAll();
        List<UserOperation> userOperations = userOpRepo.findAll();
        return OperationMapper.allOperationListToDtoList(applicationOperations, courseOperations, groupOperations,
                mentorOperations, paymentOperations, studentOperations, userOperations);
    }

    private List<OperationDto> findAllOperationsByUserId(Long userId) {
        User user = getUserEntityById(userId);
        List<ApplicationOperation> applicationOperations = applicationOpRepo.findAllByUser(user);
        List<CourseOperation> courseOperations = courseOpRepo.findAllByUser(user);
        List<GroupOperation> groupOperations = groupOpRepo.findAllByUser(user);
        List<MentorOperation> mentorOperations = mentorOpRepo.findAllByUser(user);
        List<PaymentOperation> paymentOperations = paymentOpRepo.findAllByUser(user);
        List<StudentOperation> studentOperations = studentOpRepo.findAllByUser(user);
        List<UserOperation> userOperations = userOpRepo.findAllByUser(user);
        return OperationMapper.allOperationListToDtoList(applicationOperations, courseOperations, groupOperations,
                mentorOperations, paymentOperations, studentOperations, userOperations);
    }

    private List<OperationDto> findAllOperationsByType(OperationType type) {
        List<ApplicationOperation> applicationOperations = applicationOpRepo.findAllByOperationType(type);
        List<CourseOperation> courseOperations = courseOpRepo.findAllByOperationType(type);
        List<GroupOperation> groupOperations = groupOpRepo.findAllByOperationType(type);
        List<MentorOperation> mentorOperations = mentorOpRepo.findAllByOperationType(type);
        List<PaymentOperation> paymentOperations = paymentOpRepo.findAllByOperationType(type);
        List<StudentOperation> studentOperations = studentOpRepo.findAllByOperationType(type);
        List<UserOperation> userOperations = userOpRepo.findAllByOperationType(type);
        return OperationMapper.allOperationListToDtoList(applicationOperations, courseOperations, groupOperations,
                mentorOperations, paymentOperations, studentOperations, userOperations);
    }

    private List<OperationDto> findAllOperationsByTypeAndUserId(OperationType type, Long userId) {
        User user = userId == null ? null : getUserEntityById(userId);
        List<ApplicationOperation> applicationOperations = userId == null ?
                (type == null ? applicationOpRepo.findAll() : applicationOpRepo.findAllByOperationType(type)) :
                (type == null ? applicationOpRepo.findAllByUser(user) : applicationOpRepo.findAllByUserAndOperationType(user, type));
        List<CourseOperation> courseOperations = userId == null ?
                (type == null ? courseOpRepo.findAll() : courseOpRepo.findAllByOperationType(type)) :
                (type == null ? courseOpRepo.findAllByUser(user) : courseOpRepo.findAllByUserAndOperationType(user, type));
        List<GroupOperation> groupOperations = userId == null ?
                (type == null ? groupOpRepo.findAll() : groupOpRepo.findAllByOperationType(type)) :
                (type == null ? groupOpRepo.findAllByUser(user) : groupOpRepo.findAllByUserAndOperationType(user, type));
        List<MentorOperation> mentorOperations = userId == null ?
                (type == null ? mentorOpRepo.findAll() : mentorOpRepo.findAllByOperationType(type)) :
                (type == null ? mentorOpRepo.findAllByUser(user) : mentorOpRepo.findAllByUserAndOperationType(user, type));
        List<PaymentOperation> paymentOperations = userId == null ?
                (type == null ? paymentOpRepo.findAll() : paymentOpRepo.findAllByOperationType(type)) :
                (type == null ? paymentOpRepo.findAllByUser(user) : paymentOpRepo.findAllByUserAndOperationType(user, type));
        List<StudentOperation> studentOperations = userId == null ?
                (type == null ? studentOpRepo.findAll() : studentOpRepo.findAllByOperationType(type)) :
                (type == null ? studentOpRepo.findAllByUser(user) : studentOpRepo.findAllByUserAndOperationType(user, type));
        List<UserOperation> userOperations = userId == null ?
                (type == null ? userOpRepo.findAll() : userOpRepo.findAllByOperationType(type)) :
                (type == null ? userOpRepo.findAllByUser(user) : userOpRepo.findAllByUserAndOperationType(user, type));
        return OperationMapper.allOperationListToDtoList(applicationOperations, courseOperations, groupOperations,
                mentorOperations, paymentOperations, studentOperations, userOperations);
    }

    private List<OperationDto> findAllOperationsByTargetAndTypeAndUserId(OperationTarget target, OperationType type,
                                                                         Long userId) {
        User user = userId == null ? null : getUserEntityById(userId);
        if (target == OperationTarget.APPLICATION) {
            return OperationMapper.applicationOperationListToDtoList(userId == null ?
                    (type == null ? applicationOpRepo.findAll() : applicationOpRepo.findAllByOperationType(type)) :
                    (type == null ? applicationOpRepo.findAllByUser(user) :
                            applicationOpRepo.findAllByUserAndOperationType(user, type))
            );
        }
        if (target == OperationTarget.COURSE) {
            return OperationMapper.courseOperationListToDtoList(userId == null ?
                    (type == null ? courseOpRepo.findAll() : courseOpRepo.findAllByOperationType(type)) :
                    (type == null ? courseOpRepo.findAllByUser(user) :
                            courseOpRepo.findAllByUserAndOperationType(user, type))
            );
        }
        if (target == OperationTarget.GROUP) {
            return OperationMapper.groupOperationListToDtoList(userId == null ?
                    (type == null ? groupOpRepo.findAll() : groupOpRepo.findAllByOperationType(type)) :
                    (type == null ? groupOpRepo.findAllByUser(user) :
                            groupOpRepo.findAllByUserAndOperationType(user, type))
            );
        }
        if (target == OperationTarget.MENTOR) {
            return OperationMapper.mentorOperationListToDtoList(userId == null ?
                    (type == null ? mentorOpRepo.findAll() : mentorOpRepo.findAllByOperationType(type)) :
                    (type == null ? mentorOpRepo.findAllByUser(user) :
                            mentorOpRepo.findAllByUserAndOperationType(user, type))
            );
        }
        if (target == OperationTarget.PAYMENT) {
            return OperationMapper.paymentOperationListToDtoList(userId == null ?
                    (type == null ? paymentOpRepo.findAll() : paymentOpRepo.findAllByOperationType(type)) :
                    (type == null ? paymentOpRepo.findAllByUser(user) :
                            paymentOpRepo.findAllByUserAndOperationType(user, type))
            );
        }
        if (target == OperationTarget.STUDENT) {
            return OperationMapper.studentOperationListToDtoList(userId == null ?
                    (type == null ? studentOpRepo.findAll() : studentOpRepo.findAllByOperationType(type)) :
                    (type == null ? studentOpRepo.findAllByUser(user) :
                            studentOpRepo.findAllByUserAndOperationType(user, type))
            );
        }
        return OperationMapper.userOperationListToDtoList(userId == null ?
                (type == null ? userOpRepo.findAll() : userOpRepo.findAllByOperationType(type)) :
                (type == null ? userOpRepo.findAllByUser(user) :
                        userOpRepo.findAllByUserAndOperationType(user, type))
        );
    }


    private User getCurrentUserEntity() {
        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(RuntimeException::new);
    }

    private User getUserEntityById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(EntityEnum.USER, "id", userId));
    }
}
