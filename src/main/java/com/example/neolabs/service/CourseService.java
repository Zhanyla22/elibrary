package com.example.neolabs.service;



import com.example.neolabs.dto.CourseDTO;
import com.example.neolabs.entity.Course;

import java.util.List;

public interface CourseService {
    List<CourseDTO> getAllCourses();
    CourseDTO insertCourse(CourseDTO courseDTO);
    CourseDTO deleteCourseById(Long id);
    Course getCourseEntityById(Long id);
    CourseDTO updateCourseById(Long id, CourseDTO courseDTO);
}

