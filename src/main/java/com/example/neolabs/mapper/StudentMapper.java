package com.example.neolabs.mapper;

import com.example.neolabs.dto.StudentDto;
import com.example.neolabs.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public Student dtoToEntity(StudentDto studentDto){
        return Student.builder()
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .gender(studentDto.getGender())
                .email(studentDto.getEmail())
                .phoneNumber(studentDto.getPhoneNumber())
                .status(studentDto.getStatus())
                .build();
    }
}
