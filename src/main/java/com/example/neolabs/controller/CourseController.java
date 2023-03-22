package com.example.neolabs.controller;


import com.example.neolabs.dto.CourseDTO;
import com.example.neolabs.service.impl.CourseServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/course")
@Tag(name = "Course Resource", description = "The Course API ")
public class CourseController {
    private final CourseServiceImpl courseService;

    @Operation(summary = "Get all cources")
    @GetMapping(value = {""}, produces = "application/json")
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        return ResponseEntity.ok(courseService.getAllCourses());
    }


    @Operation(summary = "Insert new course")
    @PostMapping(value = {""}, produces = "application/json")
    public ResponseEntity<CourseDTO> insertCourse(@RequestBody CourseDTO courseDTO){
        return ResponseEntity.ok(courseService.insertCourse(courseDTO));
    }

    @Operation(summary = "Delete course by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<CourseDTO> deleteCourseById(@PathVariable("id") Long id){
        return ResponseEntity.ok(courseService.deleteCourseById(id));
    }

    @PutMapping(value = {"/{id}"})
    @Operation(summary = "Update course by id")
    public ResponseEntity<CourseDTO> updateCourseById(@PathVariable("id") Long id,
                                           @Valid @RequestBody CourseDTO courseDTO){
        return ResponseEntity.ok(courseService.updateCourseById(id, courseDTO));
    }
}
