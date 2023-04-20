package com.example.neolabs.service;

import com.example.neolabs.dto.CourseCardDto;
import com.example.neolabs.dto.CourseDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.request.ArchiveRequest;
import com.example.neolabs.dto.request.create.CreateCourseRequest;
import com.example.neolabs.dto.request.update.UpdateCourseRequest;
import com.example.neolabs.entity.Course;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {

    List<CourseCardDto> getAllCourses();

    CourseDto insertCourse(CreateCourseRequest createCourseRequest);

    CourseDto deleteCourseById(Long courseId);

    Course getCourseEntityById(Long courseId);

    CourseDto getCourseById(Long courseId);

    CourseCardDto updateCourseById(Long courseId, UpdateCourseRequest updateCourseRequest);

    ResponseDto archiveCourseById(Long courseId, ArchiveRequest archiveRequest);

    ResponseDto unarchiveCourseById(Long courseId);

    CourseCardDto saveImageCourse(Long courseId, MultipartFile multipartFile);

}

