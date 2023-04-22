package com.example.neolabs.dto.response.analytics;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GeneralAnalyticsResponse {
    Long totalStudents;
    Long totalApplications;
    Long totalGroups;
    Long totalCourses;
    Long totalMentors;
    Long totalManagers;
}
