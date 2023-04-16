package com.example.neolabs.service.impl;

import com.example.neolabs.dto.CourseDto;
import com.example.neolabs.dto.ResponseDto;
import com.example.neolabs.dto.request.ArchiveRequest;
import com.example.neolabs.dto.request.create.CreateCourseRequest;
import com.example.neolabs.entity.Course;
import com.example.neolabs.enums.Status;
import com.example.neolabs.exception.BaseException;
import com.example.neolabs.exception.ContentNotFoundException;
import com.example.neolabs.mapper.CourseMapper;
import com.example.neolabs.repository.CourseRepository;
import com.example.neolabs.service.CourseService;
import com.example.neolabs.util.ResponseUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Override
    public List<CourseDto> getAllCourses() {
        return courseMapper.entityListToDtoList(courseRepository.findAll());
    }

    @Override
    public CourseDto insertCourse(CreateCourseRequest createCourseRequest) {
        Course course = courseMapper.createRequestToEntity(createCourseRequest);
        return courseMapper.entityToDto(courseRepository.save(course));
    }

    @Override
    public CourseDto updateCourseById(Long id, CreateCourseRequest createCourseRequest) {
        Course course = courseMapper.createRequestToEntity(createCourseRequest);
        course.setId(id);
        return courseMapper.entityToDto(courseRepository.save(course));
    }

    @Override
    public ResponseDto archiveCourseById(Long courseId, ArchiveRequest archiveRequest) {
        Course course = getCourseEntityById(courseId);
        if (course.getStatus() == Status.ARCHIVED) {
            throw new BaseException("course is already archived", HttpStatus.CONFLICT);
        }
        course.setReason(archiveRequest.getReason());
        course.setStatus(Status.ARCHIVED);
        courseRepository.save(course);
        return ResponseUtil.buildSuccessResponse("Course has been successfully archived.");
    }

    @Override
    public ResponseDto unarchiveCourseById(Long courseId) {
        Course course = getCourseEntityById(courseId);
        if (course.getStatus() == Status.ACTIVE) {
            throw new BaseException("Course is already active.", HttpStatus.CONFLICT);
        }
        course.setStatus(Status.ACTIVE);
        course.setReason(null);
        courseRepository.save(course);
        return ResponseUtil.buildSuccessResponse("Course has been successfully unarchived.");
    }

    @Override
    public CourseDto deleteCourseById(Long id) {
        Course course = getCourseEntityById(id);
        courseRepository.delete(course);
        return courseMapper.entityToDto(course);
    }

    @Override
    public Course getCourseEntityById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> {
            throw new ContentNotFoundException();
        });
    }

}
