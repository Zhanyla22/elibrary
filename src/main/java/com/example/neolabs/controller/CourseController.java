package com.example.neolabs.controller;


import com.example.neolabs.dto.CourseDto;
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
    public ResponseEntity<List<CourseDto>> getAllCourses(){
        return ResponseEntity.ok(courseService.getAllCourses());
    }


    @Operation(summary = "Insert new course")
    @PostMapping(value = {""}, produces = "application/json")
    public ResponseEntity<CourseDto> insertCourse(@RequestBody CourseDto courseDTO){
        return ResponseEntity.ok(courseService.insertCourse(courseDTO));
    }

    @Operation(summary = "Delete course by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<CourseDto> deleteCourseById(@PathVariable("id") Long id){
        return ResponseEntity.ok(courseService.deleteCourseById(id));
    }

    @PutMapping(value = {"/{id}"})
    @Operation(summary = "Update course by id")
    public ResponseEntity<CourseDto> updateCourseById(@PathVariable("id") Long id,
                                                      @Valid @RequestBody CourseDto courseDTO){
        return ResponseEntity.ok(courseService.updateCourseById(id, courseDTO));
    }
}
