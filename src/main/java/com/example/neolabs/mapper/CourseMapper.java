package com.example.neolabs.mapper;

import com.example.neolabs.dto.CourseCardDto;
import com.example.neolabs.dto.CourseDto;
import com.example.neolabs.dto.request.create.CreateCourseRequest;
import com.example.neolabs.dto.request.create.UpdateCourseRequest;
import com.example.neolabs.entity.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public class CourseMapper {

    public static CourseCardDto entityToCardDto(Course course) {
        return CourseCardDto.builder()
                .id(course.getId())
                .name(course.getName())
                .cost(course.getCost())
                .durationInMonth(course.getDurationInMonth())
                .imageUrl(course.getImageUrl())
                .numberOfLessons(course.getNumberOfLessons())
                .numberOfGroups(course.getGroups().size())
                .build();
    }

    public static CourseDto entityToDto(Course course) {
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
                .groups(course.getGroups().stream().map(GroupMapper::entityToCardDto).collect(Collectors.toList()))
                .build();
    }

    public static Course createRequestToEntity(CreateCourseRequest request) {
        return Course.builder()
                .name(request.getName())
                .cost(request.getCost())
                .durationInMonth(request.getDurationInMonth())
                .numberOfLessons(request.getNumberOfLessons())
                .build();
    }

    public static Course updateEntity(Course course, UpdateCourseRequest request){
        Course result = Course.builder()
                .name(request.getName() != null ? request.getName() : course.getName())
                .cost(request.getCost() != null ? request.getCost() : course.getCost())
                .numberOfLessons(request.getNumberOfLessons() != null ?
                        request.getNumberOfLessons() : course.getNumberOfLessons())
                .durationInMonth(request.getDurationInMonth() != null ?
                        request.getDurationInMonth() : course.getDurationInMonth())
                .build();
        result.setId(course.getId());
        return result;
    }

    public static List<CourseCardDto> entityListToCardDtoList(List<Course> entities) {
        return entities.stream().map(CourseMapper::entityToCardDto).collect(Collectors.toList());
    }
}