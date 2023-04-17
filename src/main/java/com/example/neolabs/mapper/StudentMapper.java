package com.example.neolabs.mapper;

import com.example.neolabs.dto.StudentDto;
import com.example.neolabs.dto.request.create.CreateStudentRequest;
import com.example.neolabs.dto.request.update.UpdateStudentRequest;
import com.example.neolabs.entity.Student;
import com.example.neolabs.enums.Status;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentMapper {

    final GroupMapper groupMapper;

    public Student createRequestToEntity(CreateStudentRequest request) {
        return Student.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .gender(request.getGender())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }

    public Student updateRequestToEntity(UpdateStudentRequest request) {
        return Student.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }

    public StudentDto entityToDto(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .email(student.getEmail())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .phoneNumber(student.getPhoneNumber())
                .gender(student.getGender().getRussian())
                .groups(groupMapper.entityListToDtoList(student.getGroups()))
                .status(student.getStatus().getRussian())
                .totalDebt(null) // FIXME: 16.03.2023
                .totalPaymentPercentage(null)
                .build();
    }

    public List<StudentDto> entityListToDtoList(List<Student> students) {
        return students.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}
