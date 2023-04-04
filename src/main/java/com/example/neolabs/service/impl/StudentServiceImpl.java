package com.example.neolabs.service.impl;

import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.StudentDto;
import com.example.neolabs.dto.request.ArchiveRequest;
import com.example.neolabs.dto.request.ConversionRequest;
import com.example.neolabs.entity.Application;
import com.example.neolabs.entity.Student;
import com.example.neolabs.enums.EntityEnum;
import com.example.neolabs.enums.OperationType;
import com.example.neolabs.enums.ResultCode;
import com.example.neolabs.exception.EntityNotFoundException;
import com.example.neolabs.mapper.ApplicationMapper;
import com.example.neolabs.mapper.StudentMapper;
import com.example.neolabs.repository.StudentRepository;
import com.example.neolabs.service.StudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentServiceImpl implements StudentService {

    final OperationServiceImpl operationService;
    final StudentRepository studentRepository;
    final StudentMapper studentMapper;
    final ApplicationMapper applicationMapper;

    @Override
    public ResponseDto insertStudent(StudentDto studentDto) {
        Student student = studentMapper.dtoToEntity(studentDto);
        operationService.recordStudentOperation(studentRepository.save(student), OperationType.CREATE);
        return ResponseDto.builder()
                .resultCode(ResultCode.SUCCESS)
                .result("Student has been successfully added to the database.")
                .build();
    }

    @Override
    public void insertStudentFromApplication(Application application, ConversionRequest conversionRequest) {
        Student student = applicationMapper.entityToStudentEntity(application, conversionRequest);
        operationService.recordStudentOperation(studentRepository.save(student), OperationType.CREATE);
    }

    @Override
    public List<StudentDto> getAllStudents(Boolean isArchived, PageRequest pageRequest) {
        Page<Student> students;
        if (isArchived != null) {
            students = studentRepository.findAllByIsArchived(isArchived, pageRequest);
        } else {
            students = studentRepository.findAll(pageRequest);
        }
        return studentMapper.entityListToDtoList(students.stream().toList());
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        return studentMapper.entityToDto(getStudentEntityById(studentId));
    }

    @Override
    public ResponseDto updateStudentById(Long studentId, StudentDto studentDto) {
        Student student = studentMapper.dtoToEntity(studentDto);
        student.setId(studentId);
        operationService.recordStudentOperation(studentRepository.save(student), OperationType.UPDATE);
        return ResponseDto.builder()
                .resultCode(ResultCode.SUCCESS)
                .result("Student with id of " + studentId + " has been successfully updated.")
                .build();
    }

    @Override
    public ResponseDto archiveStudentById(Long studentId, ArchiveRequest archiveRequest) {
        Student student = getStudentEntityById(studentId);
        if (student.getIsArchived()) {
            return ResponseDto.builder()
                    .resultCode(ResultCode.EXCEPTION)
                    .result("Student is already archived.")
                    .build();// FIXME: 29.03.2023 better to do this section with custom exception
        }
        student.setIsArchived(true);
        student.setArchiveReason(archiveRequest.getReason());
        // FIXME: 29.03.2023 need to add blacklisted here
        // FIXME: 29.03.2023 but how?
        operationService.recordStudentOperation(studentRepository.save(student), OperationType.ARCHIVE);
        return ResponseDto.builder()
                .resultCode(ResultCode.SUCCESS)
                .result("Student has been successfully archived.")
                .build();
    }

    @Override
    public ResponseDto unarchiveStudentById(Long studentId) {
        Student student = getStudentEntityById(studentId);
        if (!student.getIsArchived()) {
            return ResponseDto.builder()
                    .resultCode(ResultCode.EXCEPTION)
                    .result("Student is not archived already.")
                    .build();// FIXME: 29.03.2023 same as in the function above
        }
        student.setIsArchived(false);
        student.setArchiveReason(null);
        operationService.recordStudentOperation(studentRepository.save(student), OperationType.UNARCHIVE);
        return ResponseDto.builder()
                .resultCode(ResultCode.SUCCESS)
                .result("Student has been successfully unarchived.")
                .build();
    }

    public Student getStudentEntityById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> {
            throw new EntityNotFoundException(EntityEnum.STUDENT, "id", studentId);
        });
    }
}
