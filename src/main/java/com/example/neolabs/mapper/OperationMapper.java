package com.example.neolabs.mapper;

import com.example.neolabs.dto.OperationDto;
import com.example.neolabs.entity.operation.*;
import com.example.neolabs.enums.EntityEnum;
import com.example.neolabs.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OperationMapper {

    public static OperationDto applicationOperationToDto(ApplicationOperation operation){
        return OperationDto.builder()
                .targetId(operation.getApplication().getId())
                .targetType(EntityEnum.APPLICATION.getRussian())
                .description(operation.getDescription())
                .firstName(operation.getUser().getFirstName())
                .lastName(operation.getUser().getLastName())
                .type(operation.getOperationType())
                .rawDate(operation.getCreatedDate())
                .date(DateUtil.datetimeToDateFormatter.format(operation.getCreatedDate()))
                .time(DateUtil.datetimeFormatter.format(operation.getCreatedDate()))
                .build();
    }

    public static List<OperationDto> applicationOperationListToDtoList(List<ApplicationOperation> operations){
        return operations.stream().map(OperationMapper::applicationOperationToDto).collect(Collectors.toList());
    }

    public static OperationDto userOperationToDto(UserOperation operation){
        return OperationDto.builder()
                .targetId(operation.getTargetUser().getId())
                .targetType(EntityEnum.USER.getRussian())
                .description(operation.getDescription())
                .firstName(operation.getUser().getFirstName())
                .lastName(operation.getUser().getLastName())
                .type(operation.getOperationType())
                .rawDate(operation.getCreatedDate())
                .date(DateUtil.datetimeToDateFormatter.format(operation.getCreatedDate()))
                .time(DateUtil.datetimeFormatter.format(operation.getCreatedDate()))
                .build();
    }

    public static List<OperationDto> userOperationListToDtoList(List<UserOperation> operations){
        return operations.stream().map(OperationMapper::userOperationToDto).collect(Collectors.toList());
    }

    public static OperationDto studentOperationToDto(StudentOperation operation){
        return OperationDto.builder()
                .targetId(operation.getStudent().getId())
                .targetType(EntityEnum.STUDENT.getRussian())
                .description(operation.getDescription())
                .firstName(operation.getUser().getFirstName())
                .lastName(operation.getUser().getLastName())
                .type(operation.getOperationType())
                .rawDate(operation.getCreatedDate())
                .date(DateUtil.datetimeToDateFormatter.format(operation.getCreatedDate()))
                .time(DateUtil.datetimeFormatter.format(operation.getCreatedDate()))
                .build();
    }

    public static List<OperationDto> studentOperationListToDtoList(List<StudentOperation> operations){
        return operations.stream().map(OperationMapper::studentOperationToDto).collect(Collectors.toList());
    }

    public static OperationDto groupOperationToDto(GroupOperation operation){
        return OperationDto.builder()
                .targetId(operation.getGroup().getId())
                .targetType(EntityEnum.GROUP.getRussian())
                .description(operation.getDescription())
                .firstName(operation.getUser().getFirstName())
                .lastName(operation.getUser().getLastName())
                .type(operation.getOperationType())
                .rawDate(operation.getCreatedDate())
                .date(DateUtil.datetimeToDateFormatter.format(operation.getCreatedDate()))
                .time(DateUtil.datetimeFormatter.format(operation.getCreatedDate()))
                .build();
    }

    public static List<OperationDto> groupOperationListToDtoList(List<GroupOperation> operations){
        return operations.stream().map(OperationMapper::groupOperationToDto).collect(Collectors.toList());
    }

    public static OperationDto courseOperationToDto(CourseOperation operation){
        return OperationDto.builder()
                .targetId(operation.getCourse().getId())
                .targetType(EntityEnum.COURSE.getRussian())
                .description(operation.getDescription())
                .firstName(operation.getUser().getFirstName())
                .lastName(operation.getUser().getLastName())
                .type(operation.getOperationType())
                .rawDate(operation.getCreatedDate())
                .date(DateUtil.datetimeToDateFormatter.format(operation.getCreatedDate()))
                .time(DateUtil.datetimeFormatter.format(operation.getCreatedDate()))
                .build();
    }

    public static List<OperationDto> courseOperationListToDtoList(List<CourseOperation> operations){
        return operations.stream().map(OperationMapper::courseOperationToDto).collect(Collectors.toList());
    }

    public static OperationDto mentorOperationToDto(MentorOperation operation){
        return OperationDto.builder()
                .targetId(operation.getMentor().getId())
                .targetType(EntityEnum.MENTOR.getRussian())
                .description(operation.getDescription())
                .firstName(operation.getUser().getFirstName())
                .lastName(operation.getUser().getLastName())
                .type(operation.getOperationType())
                .rawDate(operation.getCreatedDate())
                .date(DateUtil.datetimeToDateFormatter.format(operation.getCreatedDate()))
                .time(DateUtil.datetimeFormatter.format(operation.getCreatedDate()))
                .build();
    }

    public static List<OperationDto> mentorOperationListToDtoList(List<MentorOperation> operations){
        return operations.stream().map(OperationMapper::mentorOperationToDto).collect(Collectors.toList());
    }

    public static OperationDto paymentOperationToDto(PaymentOperation operation){
        return OperationDto.builder()
                .targetId(operation.getPayment().getId())
                .targetType(EntityEnum.PAYMENT.getRussian())
                .description(operation.getDescription())
                .firstName(operation.getUser().getFirstName())
                .lastName(operation.getUser().getLastName())
                .type(operation.getOperationType())
                .rawDate(operation.getCreatedDate())
                .date(DateUtil.datetimeToDateFormatter.format(operation.getCreatedDate()))
                .time(DateUtil.datetimeFormatter.format(operation.getCreatedDate()))
                .build();
    }

    public static List<OperationDto> paymentOperationListToDtoList(List<PaymentOperation> operations){
        return operations.stream().map(OperationMapper::paymentOperationToDto).collect(Collectors.toList());
    }

    public static List<OperationDto> allOperationListToDtoList(List<ApplicationOperation> applicationOperations,
                                                               List<CourseOperation> courseOperations,
                                                               List<GroupOperation> groupOperations,
                                                               List<MentorOperation> mentorOperations,
                                                               List<PaymentOperation> paymentOperations,
                                                               List<StudentOperation> studentOperations,
                                                               List<UserOperation> userOperations){
        List<OperationDto> operations = new ArrayList<>();
        if (applicationOperations != null){
            operations.addAll(applicationOperationListToDtoList(applicationOperations));
        }
        if (userOperations != null){
            operations.addAll(userOperationListToDtoList(userOperations));
        }
        if (groupOperations != null){
            operations.addAll(groupOperationListToDtoList(groupOperations));
        }
        if (studentOperations != null){
            operations.addAll(studentOperationListToDtoList(studentOperations));
        }
        if (courseOperations != null){
            operations.addAll(courseOperationListToDtoList(courseOperations));
        }
        if (mentorOperations != null){
            operations.addAll(mentorOperationListToDtoList(mentorOperations));
        }
        if (paymentOperations != null){
            operations.addAll(paymentOperationListToDtoList(paymentOperations));
        }
        Collections.sort(operations);
        return operations;
    }
}
