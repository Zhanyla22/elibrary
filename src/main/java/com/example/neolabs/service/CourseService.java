package com.example.neolabs.service;

import com.example.neolabs.dto.CourseDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.request.ArchiveRequest;
import com.example.neolabs.dto.request.create.CreateCourseRequest;
import com.example.neolabs.entity.Course;

import java.util.List;

public interface CourseService {

    List<CourseDto> getAllCourses();

    CourseDto insertCourse(CreateCourseRequest createCourseRequest);

    CourseDto deleteCourseById(Long courseId);

    Course getCourseEntityById(Long courseId);

    CourseDto updateCourseById(Long courseId, CreateCourseRequest createCourseRequest);

    ResponseDto archiveCourseById(Long courseId, ArchiveRequest archiveRequest);

    ResponseDto unarchiveCourseById(Long courseId);
}

