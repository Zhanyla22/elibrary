package com.example.neolabs.controller;

import com.example.neolabs.dto.ArchiveDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.StudentDto;
import com.example.neolabs.dto.request.create.CreateStudentRequest;
import com.example.neolabs.dto.request.update.UpdateStudentRequest;
import com.example.neolabs.enums.Status;
import com.example.neolabs.service.impl.StudentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentServiceImpl studentService;

    @Operation(summary = "Find Student by ID")
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("studentId") Long studentId){
        return ResponseEntity.ok(studentService.getStudentById(studentId));
    }

    @Operation(summary = "Find all Students")
    @GetMapping("")
    public ResponseEntity<List<StudentDto>> getALlStudents(@RequestParam(name = "status", required = false) Status status,
                                                           @RequestParam("sortBy") Optional<String> sortBy,
                                                           @RequestParam("size") Optional<Integer> size,
                                                           @RequestParam("page") Optional<Integer> page){
        return ResponseEntity.ok(studentService.getAllStudents(status,
                PageRequest.of(page.orElse(0), size.orElse(20), Sort.by(sortBy.orElse("id")))));
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto> insertStudent(@RequestBody @Valid CreateStudentRequest createStudentRequest){
        return ResponseEntity.ok(studentService.insertStudent(createStudentRequest));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<StudentDto>> filterStudents(@RequestParam("groupId") Optional<Long> groupId,
                                                           @RequestParam("status") Optional<Status> status){
        return ResponseEntity.ok(studentService.filter(groupId.orElse(null), status.orElse(null)));
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentDto>> searchStudents(@RequestParam("email") Optional<String> email,
                                                           @RequestParam("firstName") Optional<String> firstName,
                                                           @RequestParam("lastName") Optional<String> lastName,
                                                           @RequestParam("firstOrLastName") Optional<String> firstOrLastName,
                                                           @RequestParam("phoneNumber") Optional<String> phoneNumber){
        return ResponseEntity.ok(studentService.search(email.orElse(null), firstName.orElse(null),
                lastName.orElse(null), firstOrLastName.orElse(null), phoneNumber.orElse(null)));
    }

    @PutMapping("/enroll")
    public ResponseEntity<ResponseDto> enrollStudentToGroup(@RequestParam("student_id") Long studentId,
                                                            @RequestParam("group_id") Long groupId){
        return ResponseEntity.ok(studentService.enrollStudent(studentId, groupId));
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<ResponseDto> updateStudentById(@PathVariable("studentId") Long studentId,
                                                         @RequestBody UpdateStudentRequest updateStudentRequest){
        return ResponseEntity.ok(studentService.updateStudentById(studentId, updateStudentRequest));
    }

    @Operation(summary = "archive student by id")
    @PutMapping("/archive/{id}")
    public void archiveStudentById(@PathVariable Long id, @RequestBody ArchiveDto studentArchiveDto) {
        studentService.archiveStudentById(id, studentArchiveDto);
    }

    @Operation(summary = "blacklist student by id")
    @PutMapping("/blacklist/{id}")
    public void blackListStudentById(@PathVariable Long id, @RequestBody ArchiveDto studentBlacklistDto) {
        studentService.blacklistStudentById(id, studentBlacklistDto);
    }
}
