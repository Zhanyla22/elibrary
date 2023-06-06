package com.example.neolabs.service;

import com.example.neolabs.dto.request.CourseRequest;
import com.example.neolabs.dto.response.CourseResponse;

import java.util.List;

public interface CourseService {

    List<CourseResponse> getAllCourses();

    void  addNewCourse(CourseRequest courseRequest);
}
