package com.example.neolabs.mapper;

import com.example.neolabs.dto.response.analytics.CourseAnalyticsDto;
import com.example.neolabs.entity.operation.ICourseEnrollmentCount;

import java.util.List;
import java.util.stream.Collectors;

public class AnalyticsMapper {
    public static List<CourseAnalyticsDto> courseEnrollmentListToDtoList(List<ICourseEnrollmentCount> enrollments, Long totalEnrollments){
        return enrollments.stream().map(enrollmentCount -> courseEnrollmentToDto(enrollmentCount, totalEnrollments))
                .collect(Collectors.toList());
    }

    public static CourseAnalyticsDto courseEnrollmentToDto(ICourseEnrollmentCount enrollmentCount, Long totalEnrollments){
        return CourseAnalyticsDto.builder()
                .courseName(enrollmentCount.getCourseName())
                .enrollments(enrollmentCount.getTotalEnrollments())
                .percentage(totalEnrollments.doubleValue() / enrollmentCount.getTotalEnrollments())
                .build();
    }
}
