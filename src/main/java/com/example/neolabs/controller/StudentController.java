package com.example.neolabs.controller;

import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.StudentDto;
import com.example.neolabs.dto.request.ArchiveRequest;
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
    public ResponseEntity<List<StudentDto>> getALlStudents(@RequestParam("sortBy") Optional<String> sortBy,
                                                           @RequestParam("size") Optional<Integer> size,
                                                           @RequestParam("page") Optional<Integer> page){
        return ResponseEntity.ok(studentService.getAllStudents(
                PageRequest.of(page.orElse(0), size.orElse(1000), Sort.by(sortBy.orElse("id")))));
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto> insertStudent(@RequestBody @Valid CreateStudentRequest createStudentRequest){
        return ResponseEntity.ok(studentService.insertStudent(createStudentRequest));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<StudentDto>> filterStudents(@RequestParam("groupId") Optional<Long> groupId,
                                                           @RequestParam("status") Optional<Status> status,
                                                           @RequestParam("sortBy") Optional<String> sortBy,
                                                           @RequestParam("size") Optional<Integer> size,
                                                           @RequestParam("page") Optional<Integer> page){
        return ResponseEntity.ok(studentService.filter(groupId.orElse(null), status.orElse(null),
                PageRequest.of(page.orElse(0), size.orElse(20), Sort.by(sortBy.orElse("id")))));
    }

    @GetMapping("/find")
    public ResponseEntity<List<StudentDto>> searchStudents(@RequestParam("string") Optional<String> searchString,
                                                           @RequestParam("status") Optional<Status> status,
                                                           @RequestParam("groupId") Optional<Long> groupId,
                                                           @RequestParam("isArchived") Optional<Boolean> isArchived,
                                                           @RequestParam("sortBy") Optional<String> sortBy,
                                                           @RequestParam("size") Optional<Integer> size,
                                                           @RequestParam("page") Optional<Integer> page){
        return ResponseEntity.ok(studentService.find(searchString.orElse(null),
                status.orElse(null), groupId.orElse(null), isArchived.orElse(false),
                PageRequest.of(page.orElse(0), size.orElse(1000), Sort.by(sortBy.orElse("id")))));
    }

    @PutMapping("/enroll")
    public ResponseEntity<ResponseDto> enrollStudentToGroup(@RequestParam("studentId") Long studentId,
                                                            @RequestParam("groupId") Long groupId){
        return ResponseEntity.ok(studentService.enrollStudent(studentId, groupId));
    }

    @Operation(summary = "Обновить данные студента по ID (не работает)")
    @PutMapping("/{studentId}")
    public ResponseEntity<ResponseDto> updateStudentById(@PathVariable("studentId") Long studentId,
                                                         @RequestBody UpdateStudentRequest updateStudentRequest){
        return ResponseEntity.ok(studentService.updateStudentById(studentId, updateStudentRequest));
    }

    @Operation(summary = "archive student by id")
    @PutMapping("/archive")
    public ResponseEntity<ResponseDto> archiveGroupById(@RequestParam("studentId") Long studentId,
                                 @RequestParam(value = "blacklist", defaultValue = "0") Boolean isBlacklist,
                                 @RequestBody ArchiveRequest archiveRequest) {
        return ResponseEntity.ok(studentService.archiveStudentById(studentId, archiveRequest, isBlacklist));
    }

    @Operation(summary = "Заморозить студента")
    @PutMapping("/freeze")
    public ResponseEntity<ResponseDto> freezeStudentById(@RequestParam("studentId") Long studentId,
                                                         @RequestBody ArchiveRequest archiveRequest){
        return ResponseEntity.ok(studentService.freezeStudentById(studentId, archiveRequest));
    }

    @Operation(summary = "Разморозить студента")
    @PutMapping("/unfreeze")
    public ResponseEntity<ResponseDto> freezeStudentById(@RequestParam("studentId") Long studentId){
        return ResponseEntity.ok(studentService.unfreezeStudentById(studentId));
    }

    @PutMapping
    public ResponseEntity<ResponseDto> unarchiveStudentById(@RequestParam("studentId") Long studentId){
        return ResponseEntity.ok(studentService.unarchiveStudentById(studentId));
    }

}
