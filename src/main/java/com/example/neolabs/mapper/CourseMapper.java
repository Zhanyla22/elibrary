package com.example.neolabs.mapper;

import com.example.neolabs.dto.CourseDto;
import com.example.neolabs.entity.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CourseMapper {

    private final DepartmentMapper departmentMapper;

    public CourseDto entityToDto(Course course) {
        return CourseDto.builder()
                .id(course.getId())
                .name(course.getName())
                .cost(course.getCost())
                .duration_in_month(course.getDurationInMonth())
                .numberOfLessons(course.getNumberOfLessons())
                .build();
    }

    public Course dtoToEntity(CourseDto courseDTO) {
        return Course.builder()
                .name(courseDTO.getName())
                .cost(courseDTO.getCost())
                .durationInMonth(courseDTO.getDuration_in_month())
                .numberOfLessons(courseDTO.getNumberOfLessons())
                .build();
    }

    public List<CourseDto> entityListToDtoList(List<Course> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}