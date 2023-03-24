package com.example.neolabs.controller;

import com.example.neolabs.dto.StudentDto;
import com.example.neolabs.service.impl.StudentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(name = "/api/v1/students")
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
    public ResponseEntity<List<StudentDto>> getALlStudents(@RequestParam("include_archived") Boolean includeArchived,
                                                           @RequestParam("sort_by")Optional<String> sortBy,
                                                           @RequestParam("size") Optional<Integer> size,
                                                           @RequestParam("page") Optional<Integer> page){
        return ResponseEntity.ok(studentService.getAllStudents(includeArchived,
                PageRequest.of(page.orElse(0), size.orElse(20), Sort.by(sortBy.orElse("id")))));
    }
}
