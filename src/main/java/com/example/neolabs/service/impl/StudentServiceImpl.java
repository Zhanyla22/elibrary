package com.example.neolabs.service.impl;

import com.example.neolabs.dto.ArchiveDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.StudentDto;
import com.example.neolabs.dto.request.ConversionRequest;
import com.example.neolabs.dto.request.create.CreateStudentRequest;
import com.example.neolabs.dto.request.update.UpdateStudentRequest;
import com.example.neolabs.entity.Application;
import com.example.neolabs.entity.Group;
import com.example.neolabs.entity.Student;
import com.example.neolabs.enums.*;
import com.example.neolabs.exception.BaseException;
import com.example.neolabs.exception.EntityNotFoundException;
import com.example.neolabs.mapper.ApplicationMapper;
import com.example.neolabs.mapper.StudentMapper;
import com.example.neolabs.repository.StudentRepository;
import com.example.neolabs.service.StudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
    public ResponseDto insertStudent(CreateStudentRequest createStudentRequest) {
        Student student = studentMapper.createRequestToEntity(createStudentRequest);
        if (student.getGroups() == null){
            student.setGroups(new ArrayList<>());
        }
        student.getGroups().add(groupService.getGroupEntityById(createStudentRequest.getEnrollmentGroupId()));
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
    public List<StudentDto> filter(Long groupId, Status status) {
        ExampleMatcher exampleMatcher = getFilterExampleMatcher();
        Student probe = Student.builder()
                .status(status)
                .groups(List.of(groupService.getGroupEntityById(groupId)))
                .build();
        return studentMapper.entityListToDtoList(studentRepository.findAll(Example.of(probe, exampleMatcher)));
    }

    @Override
    public List<StudentDto> search(String email, String firstName, String lastName, String firstOrLastName,
                                   String phoneNumber) {
        ExampleMatcher exampleMatcher = getSearchExampleMatcher();
        if (firstOrLastName != null) {
            firstName = firstOrLastName;
            lastName = firstOrLastName;
        }
        Student probe = Student.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .build();
        return studentMapper.entityListToDtoList(studentRepository.findAll(Example.of(probe, exampleMatcher)));
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        return studentMapper.entityToDto(getStudentEntityById(studentId));
    }

    @Override
    public ResponseDto updateStudentById(Long studentId, UpdateStudentRequest request) {
        Student student = studentMapper.updateRequestToEntity(request);
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

    private ExampleMatcher getFilterExampleMatcher(){
        return ExampleMatcher.matchingAll()
                .withMatcher("status", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("group", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("id");
    }

    private ExampleMatcher getSearchExampleMatcher(){
        return ExampleMatcher.matchingAny()
                .withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("lastName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("phoneNumber", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withIgnorePaths("id");
    }
}
