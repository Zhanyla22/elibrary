package com.example.neolabs.dto.response.analytics;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseAnalyticsResponse {
    Long totalCourses;
    Long totalEnrollments;
    List<CourseAnalyticsDto> courses;
}
