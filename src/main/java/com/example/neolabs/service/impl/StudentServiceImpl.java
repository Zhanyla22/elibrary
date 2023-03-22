package com.example.neolabs.service.impl;

import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.StudentDto;
import com.example.neolabs.dto.request.ArchiveRequest;
import com.example.neolabs.dto.request.ConversionRequest;
import com.example.neolabs.entity.Application;
import com.example.neolabs.entity.Student;
import com.example.neolabs.enums.EntityEnum;
import com.example.neolabs.exception.EntityNotFoundException;
import com.example.neolabs.mapper.ApplicationMapper;
import com.example.neolabs.mapper.StudentMapper;
import com.example.neolabs.repository.StudentRepository;
import com.example.neolabs.service.StudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentServiceImpl implements StudentService {

    final ApplicationMapper applicationMapper;
    final StudentRepository studentRepository;
    final StudentMapper studentMapper;

    @Override
    public ResponseDto insertStudent(StudentDto studentDto) {
        Student student = studentMapper.dtoToEntity(studentDto);
        return null;
    }

    @Override
    public void insertStudentFromApplication(Application application, ConversionRequest conversionRequest) {
        Student student = applicationMapper.entityToStudentEntity(application, conversionRequest);
        // FIXME: 16.03.2023
        studentRepository.save(student);
    }

    @Override
    public List<StudentDto> getAllStudents(Boolean includeArchived, PageRequest pageRequest) {
        // TODO: 16.03.2023 add archive including/excluding
        return studentMapper.entityListToDtoList(studentRepository.findAll(pageRequest).stream().toList());
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        return studentMapper.entityToDto(getStudentEntityById(studentId));
    }

    @Override
    public ResponseDto updateStudentById(Long studentId, StudentDto studentDto) {
        return null;
    }

    @Override
    public ResponseDto archiveStudentById(Long studentId, ArchiveRequest archiveRequest) {
        return null;
    }

    @Override
    public ResponseDto unarchiveStudentById(Long studentId) {
        return null;
    }

    public Student getStudentEntityById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> {
            throw new EntityNotFoundException(EntityEnum.STUDENT, "id", studentId);
        });
    }
}
