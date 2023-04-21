package com.example.neolabs.service.impl;

import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.StudentDto;
import com.example.neolabs.dto.request.ArchiveRequest;
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
import com.example.neolabs.util.DateUtil;
import com.example.neolabs.util.ResponseUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.*;
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
    final ApplicationMapper applicationMapper;

    @Override
    public ResponseDto insertStudent(CreateStudentRequest createStudentRequest) {
        Student student = StudentMapper.createRequestToEntity(createStudentRequest);
        if (student.getGroups() == null){
            student.setGroups(new ArrayList<>());
        }
        List<Group> groups = student.getGroups();
        groups.add(groupService.getGroupEntityById(createStudentRequest.getEnrollmentGroupId()));
        student.setGroups(groups);
        student.setStatus(Status.ACTIVE);
        student.setIsArchived(false);
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
    public List<StudentDto> getAllStudents(PageRequest pageRequest) {
        Page<Student> students;

        students = studentRepository.findAll(pageRequest);

        return StudentMapper.entityListToDtoList(students.stream().toList());
    }

    @Override
    public List<StudentDto> filter(Long groupId, Status status, PageRequest pageRequest) {
        Page<Student> students;
        if (groupId == null && status == null) {
            return getAllStudents(pageRequest);
        } else if (groupId == null) {
            students = studentRepository.findAllByStatus(status, pageRequest);
        } else if (status == null){
            groupService.getGroupEntityById(groupId); //checking for group's existence
            students = studentRepository.findAllByGroupId(groupId, pageRequest);
        } else {
            groupService.getGroupEntityById(groupId);
            students = studentRepository.findAllByStatusAndGroupId(status.toString(), groupId, pageRequest);
        }
        return StudentMapper.entityListToDtoList(students.stream().toList());
    }

    @Override
    public List<StudentDto> find(String searchString, Status status, Long groupId, PageRequest pageRequest) {
        boolean isArchived = status == Status.ARCHIVED;
        if (searchString == null) {
            return filter(groupId, status, pageRequest);
        }

        List<Student> students;

        if (groupId == null && status == null) {
            students = studentRepository.searchWithoutFilters(searchString, isArchived, pageRequest);
        } else if (groupId == null) {
            students = studentRepository.searchWithoutGroupId(searchString, status.toString(), isArchived, pageRequest);
        } else if (status == null) {
            groupService.getGroupEntityById(groupId);
            students = studentRepository.searchWithoutStatus(searchString, groupId, isArchived, pageRequest);
        } else {
            groupService.getGroupEntityById(groupId);
            students = studentRepository.search(searchString, status.toString(), groupId, isArchived, pageRequest);
        }
        return StudentMapper.entityListToDtoList(students);
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        return StudentMapper.entityToDto(getStudentEntityById(studentId));
    }

    @Override
    public ResponseDto freezeStudentById(Long studentId, ArchiveRequest archiveRequest) {
        Student student = getStudentEntityById(studentId);
        if (student.getStatus() == Status.FROZEN) {
            throw new BaseException("Student is already frozen.", HttpStatus.CONFLICT);
        }
        student.setStatus(Status.FROZEN);
        student.setIsArchived(false);
        student.setReason(archiveRequest.getReason());
        studentRepository.save(student);
        return ResponseUtil.buildSuccessResponse("Student has been successfully frozen.");
    }

    @Override
    public ResponseDto unfreezeStudentById(Long studentId) {
        // TODO: 17.04.2023  
        return null;
    }

    @Override
    public ResponseDto updateStudentById(Long studentId, UpdateStudentRequest request) {
        Student student = getStudentEntityById(studentId);
        if (request.getEmail() != null && student.getEmail().equals(request.getEmail())) {
            if (studentRepository.existsByEmail(request.getEmail())){
                throw new BaseException("Given email is already used.", HttpStatus.CONFLICT);
            }
        }
        student = StudentMapper.updateEntityWithUpdateRequest(student, request);
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
        List<Group> groups = student.getGroups();
        if (groups.contains(group)) {
            throw new BaseException("Student is already enrolled to the group.", HttpStatus.CONFLICT);
        }
        groups.add(group);
        student.setGroups(groups);
        operationService.recordEnrollmentOperation(group, studentRepository.save(student).getId());
        return ResponseDto.builder()
                .resultCode(ResultCode.SUCCESS)
                .result("Student has been successfully enrolled to the group.")
                .build();
    }

    @Override
    public ResponseDto archiveStudentById(Long studentId, ArchiveRequest archiveRequest, Boolean isBlacklist) {
        Student student = getStudentEntityById(studentId);
        if (student.getStatus() == Status.ARCHIVED && !isBlacklist) {
            throw new BaseException("Student is already archived", HttpStatus.CONFLICT);
        }
        if (student.getStatus() == Status.BLACKLIST && isBlacklist) {
            throw new BaseException("Student is already in blacklist", HttpStatus.CONFLICT);
        }
        student.setReason(archiveRequest.getReason());
        student.setStatus(isBlacklist ? Status.BLACKLIST : Status.ARCHIVED);
        student.setArchiveDate(LocalDateTime.now(DateUtil.getZoneId()));
        student.setIsArchived(true);
        operationService.recordStudentOperation(studentRepository.save(student), OperationType.ARCHIVE);
        return ResponseUtil.buildSuccessResponse("Student has been successfully " + (isBlacklist ? "blacklisted." : "archived."));
    }

    @Override
    public ResponseDto unarchiveStudentById(Long studentId) {
        Student student = getStudentEntityById(studentId);
        if (student.getStatus() == Status.ACTIVE) {
            throw new BaseException("Student is already active.", HttpStatus.CONFLICT);
        }
        student.setStatus(Status.ACTIVE);
        student.setReason(null);
        student.setArchiveDate(null);
        student.setIsArchived(false);
        operationService.recordStudentOperation(studentRepository.save(student), OperationType.UNARCHIVE);
        return ResponseUtil.buildSuccessResponse("Student has been successfully unarchived.");
    }

    @Override
    public List<Student> getBlacklist(){
        return studentRepository.findAllByStatus(Status.BLACKLIST);
    }


    public Student getStudentEntityById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> {
            throw new EntityNotFoundException(EntityEnum.STUDENT, "id", studentId);
        });
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
