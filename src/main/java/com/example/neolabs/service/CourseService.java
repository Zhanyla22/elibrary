package com.example.neolabs.service;

import com.example.neolabs.dto.CourseDto;
import com.example.neolabs.entity.Course;

import java.util.List;

public interface CourseService {

    List<CourseDto> getAllCourses();

    CourseDto insertCourse(CourseDto courseDTO);

    CourseDto deleteCourseById(Long id);

    Course getCourseEntityById(Long id);

    CourseDto updateCourseById(Long id, CourseDto courseDTO);
}

