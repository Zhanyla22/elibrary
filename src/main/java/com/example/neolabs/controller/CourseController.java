package com.example.neolabs.controller;


import com.example.neolabs.controller.base.BaseController;
import com.example.neolabs.dto.CourseDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.request.ArchiveRequest;
import com.example.neolabs.dto.request.create.CreateCourseRequest;
import com.example.neolabs.service.impl.CourseServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/courses")
@Tag(name = "Course Resource", description = "The Course API ")
public class CourseController extends BaseController {

    private final CourseServiceImpl courseService;

    @Operation(summary = "Get all courses")
    @GetMapping(value = {""}, produces = "application/json")
    public ResponseEntity<List<CourseDto>> getAllCourses(){
        return ResponseEntity.ok(courseService.getAllCourses());
    }


    @Operation(summary = "Insert new course")
    @PostMapping(value = {""}, produces = "application/json")
    public ResponseEntity<CourseDto> insertCourse(@RequestBody CreateCourseRequest createCourseRequest){
        return ResponseEntity.ok(courseService.insertCourse(createCourseRequest));
    }

    @Operation(summary = "Save image for course")
    @PostMapping("/save-image/{courseId}")
    public ResponseEntity<ResponseDto> saveImage(@PathVariable Long courseId,
                                                 @RequestPart MultipartFile multipartFile){
        return constructSuccessResponse(courseService.saveImageCourse(courseId, multipartFile));
    }

    @PutMapping(value = {"/{courseId}"})
    @Operation(summary = "Update course by id")
    public ResponseEntity<CourseDto> updateCourseById(@PathVariable("courseId") Long id,
                                                      @Valid @RequestBody CreateCourseRequest createCourseRequest){
        return ResponseEntity.ok(courseService.updateCourseById(id, createCourseRequest));
    }

    @PutMapping("/archive")
    public ResponseEntity<ResponseDto> archiveCourseById(@RequestParam("courseId") Long courseId,
                                                         @RequestBody @Valid ArchiveRequest archiveRequest){
        return ResponseEntity.ok(courseService.archiveCourseById(courseId, archiveRequest));
    }

    @PutMapping("/unarchive")
    public ResponseEntity<ResponseDto> unarchiveCourseById(@RequestParam("courseId") Long courseId){
        return ResponseEntity.ok(courseService.unarchiveCourseById(courseId));
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable("courseId") Long courseId){
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }
}
