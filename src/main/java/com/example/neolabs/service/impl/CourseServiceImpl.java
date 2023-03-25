package com.example.neolabs.service.impl;

import com.example.neolabs.dto.CourseDto;
import com.example.neolabs.entity.Course;
import com.example.neolabs.exception.ContentNotFoundException;
import com.example.neolabs.mapper.CourseMapper;
import com.example.neolabs.repository.CourseRepository;
import com.example.neolabs.service.CourseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
    public CourseDto insertCourse(CourseDto courseDTO) {
        Course course = courseMapper.dtoToEntity(courseDTO);
        return courseMapper.entityToDto(courseRepository.save(course));
    }

    @Override
    public CourseDto updateCourseById(Long id, CourseDto courseDTO) {
        Course course = courseMapper.dtoToEntity(courseDTO);
        course.setId(id);
        return courseMapper.entityToDto(courseRepository.save(course));
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
