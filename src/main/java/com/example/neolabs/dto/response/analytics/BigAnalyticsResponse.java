package com.example.neolabs.dto.response.analytics;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BigAnalyticsResponse {
    GeneralAnalyticsResponse general;
    ApplicationAnalyticsResponse application;
    CourseAnalyticsResponse course;
    GenderAnalyticsResponse gender;
}
