package com.example.neolabs.mapper;

import com.example.neolabs.dto.response.CourseResponse;
import com.example.neolabs.dto.response.SubjectListResponse;
import com.example.neolabs.entity.Course;
import com.example.neolabs.entity.Subject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CourseMapper {

    public static CourseResponse entityToDto(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .courseName(course.getGroupss()+" "+course.getName())
                .build();
    }

    public static List<CourseResponse> entityListToDtoList(List<Course> courses) {
        return courses.stream().map(CourseMapper::entityToDto).collect(Collectors.toList());
    }

}
