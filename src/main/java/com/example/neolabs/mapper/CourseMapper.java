package com.example.neolabs.mapper;

import com.example.neolabs.dto.CourseDto;
import com.example.neolabs.dto.request.create.CreateCourseRequest;
import com.example.neolabs.entity.Course;
import com.example.neolabs.entity.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CourseMapper {

    public CourseDto entityToDto(Course course) {
        return CourseDto.builder()
                .id(course.getId())
                .name(course.getName())
                .cost(course.getCost())
                .durationInMonth(course.getDurationInMonth())
                .isArchived(course.getIsArchived())
                .imageUrl(course.getImageUrl())
                .numberOfLessons(course.getNumberOfLessons())
                .numberOfGroups(course.getGroups().size())
                .numberOfMentors(course.getMentors().size())
                .numberOfStudents(course.getGroups().stream().mapToInt(group -> group.getStudents().size()).sum())
                .build();
    }

    public Course createRequestToEntity(CreateCourseRequest request) {
        return Course.builder()
                .name(request.getName())
                .cost(request.getCost())
                .durationInMonth(request.getDurationInMonth())
                .numberOfLessons(request.getNumberOfLessons())
                .imageUrl(request.getImageUrl())
                .build();
    }

    public List<CourseDto> entityListToDtoList(List<Course> entities) {
        return entities.stream().map(this::entityToDto).collect(Collectors.toList());
    }
}