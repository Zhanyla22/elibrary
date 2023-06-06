package com.example.neolabs.controller;

import com.example.neolabs.dto.request.CourseRequest;
import com.example.neolabs.dto.response.CourseResponse;
import com.example.neolabs.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course")
@Tag(name = "Курс", description = "API Курсы ")
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/all")
    @Operation(summary = "все курсы")
    public List<CourseResponse> getAllCourses(){
        return courseService.getAllCourses();
    }

    @PostMapping("/add")
    @Operation(summary="добавить новый курс")
    public void addNewCourse(@RequestBody CourseRequest courseRequest){
        courseService.addNewCourse(courseRequest);
    }
}
