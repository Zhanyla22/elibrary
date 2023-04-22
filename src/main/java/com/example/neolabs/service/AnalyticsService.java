package com.example.neolabs.service;

import com.example.neolabs.dto.response.analytics.*;

public interface AnalyticsService {
    GeneralAnalyticsResponse getGeneralAnalytics();
    GenderAnalyticsResponse getGenderAnalytics();
    CourseAnalyticsResponse getCourseAnalytics();
    ApplicationAnalyticsResponse getApplicationAnalytics();
    BigAnalyticsResponse getAllAnalytics();
}
