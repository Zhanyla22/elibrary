package com.example.neolabs.mapper;

import com.example.neolabs.dto.CourseDTO;
import com.example.neolabs.entity.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CourseMapper {
    private final DepartmentMapper departmentMapper;

    public CourseDTO entityToDto(Course course){
        return CourseDTO.builder()
                .id(course.getId())
                .name(course.getName())
                .level(course.getLevel())
                .department(departmentMapper.entityToDto(course.getDepartment()))
                .cost(course.getCost())
                .duration_in_month(course.getDurationInMonth())
                .status(course.getStatus())
                .build();
    }

    public Course dtoToEntity(CourseDTO courseDTO){
        return Course.builder()
                .name(courseDTO.getName())
                .level(courseDTO.getLevel())
                .department(departmentMapper.dtoToEntity(courseDTO.getDepartment()))
                .cost(courseDTO.getCost())
                .durationInMonth(courseDTO.getDuration_in_month())
                .status(courseDTO.getStatus())
                .build();
    }

    public List<CourseDTO> entityListToDtoList(List<Course> entities){
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}