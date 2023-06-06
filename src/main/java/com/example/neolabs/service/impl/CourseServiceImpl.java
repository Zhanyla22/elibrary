package com.example.neolabs.service.impl;

import com.example.neolabs.dto.request.CourseRequest;
import com.example.neolabs.dto.response.CourseResponse;
import com.example.neolabs.entity.Course;
import com.example.neolabs.entity.Subject;
import com.example.neolabs.enums.Status;
import com.example.neolabs.exception.BaseException;
import com.example.neolabs.mapper.CourseMapper;
import com.example.neolabs.mapper.SubjectListMapper;
import com.example.neolabs.repository.CourseRepository;
import com.example.neolabs.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public List<CourseResponse> getAllCourses() {
        return CourseMapper.entityListToDtoList(
                courseRepository.findAllByStatus(Status.ACTIVE));

    }

    @Override
    public void addNewCourse(CourseRequest courseRequest) {
        if(!courseRepository.existsByNameAndGroupss(courseRequest.getName(), courseRequest.getGroup()))
            courseRepository.save(Course.builder()
                    .name(courseRequest.getName())
                    .groupss(courseRequest.getGroup())
                    .status(Status.ACTIVE)
                    .build());
        else throw new BaseException("already excits", HttpStatus.NOT_ACCEPTABLE);

    }
}
