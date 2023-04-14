package com.example.neolabs.service.impl;

import com.example.neolabs.dto.ArchiveDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.StudentDto;
import com.example.neolabs.dto.request.ConversionRequest;
import com.example.neolabs.entity.Application;
import com.example.neolabs.entity.Group;
import com.example.neolabs.entity.Student;
import com.example.neolabs.enums.EntityEnum;
import com.example.neolabs.enums.OperationType;
import com.example.neolabs.enums.ResultCode;
import com.example.neolabs.enums.Status;
import com.example.neolabs.exception.BaseException;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentServiceImpl implements StudentService {

    final OperationServiceImpl operationService;
    final GroupServiceImpl groupService;
    final StudentRepository studentRepository;
    final StudentMapper studentMapper;
    final ApplicationMapper applicationMapper;

    @Override
    public ResponseDto insertStudent(StudentDto studentDto) {
        Student student = studentMapper.dtoToEntity(studentDto);
        if (student.getGroups() == null){
            student.setGroups(new ArrayList<>());
        }
        student.getGroups().add(groupService.getGroupEntityById(studentDto.getEnrollmentGroupId()));
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
    public List<StudentDto> getAllStudents(Status status, PageRequest pageRequest) {
        Page<Student> students;
        if (status != null) {
            students = studentRepository.findAllByStatus(status, pageRequest);
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
    public ResponseDto enrollStudent(Long studentId, Long groupId) {
        Student student = getStudentEntityById(studentId);
        Group group = groupService.getGroupEntityById(groupId);
        if (student.getGroups().contains(group)) {
            throw new BaseException("Student is already enrolled to the group.", HttpStatus.CONFLICT);
        }
        if (student.getGroups() == null){
            student.setGroups(new ArrayList<>());
        }
        student.getGroups().add(group);
        operationService.recordEnrollmentOperation(studentRepository.save(student), groupId);
        return ResponseDto.builder()
                .resultCode(ResultCode.SUCCESS)
                .result("Student has been successfully enrolled to the group.")
                .build();
    }

    @Override
    public void archiveStudentById(Long studentId, ArchiveDto archiveStudentDto) {
        Student student = getStudentEntityById(studentId);
        student.setUpdatedDate(LocalDateTime.now());
        student.setReason(archiveStudentDto.getReason());
        student.setStatus(Status.ARCHIVED);

        studentRepository.save(student);
    }

    @Override
    public void blacklistStudentById(Long studentId, ArchiveDto blacklistStudentDto) {
        Student student = getStudentEntityById(studentId);
        student.setUpdatedDate(LocalDateTime.now());
        student.setReason(blacklistStudentDto.getReason());
        student.setStatus(Status.BLACK_LIST);

        studentRepository.save(student);
    }

    public Student getStudentEntityById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> {
            throw new EntityNotFoundException(EntityEnum.STUDENT, "id", studentId);
        });
    }
}
