package com.example.neolabs.service.impl;

import com.example.neolabs.dto.response.analytics.*;
import com.example.neolabs.entity.Student;
import com.example.neolabs.repository.StudentRepository;
import com.example.neolabs.service.AnalyticsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnalyticsServiceImpl implements AnalyticsService {

    final StudentRepository studentRepository;

    @Override
    public GeneralAnalyticsResponse getGeneralAnalytics() {
        return null;
    }

    @Override
    public GenderAnalyticsResponse getGenderAnalytics() {
        List<Student> students = studentRepository.findAll();
        return null;
    }

    @Override
    public CourseAnalyticsResponse getCourseAnalytics() {
        // TODO: 16.04.2023 analyzing enrollment operations
        return null;
    }

    @Override
    public ApplicationAnalyticsResponse getApplicationAnalytics() {
        // TODO: 16.04.2023 need to add separate entity for this one
        //      something like ApplicationStatusHistory
        return null;
    }

    @Override
    public BigAnalyticsResponse getAllAnalytics() {
        return BigAnalyticsResponse.builder()
                .application(getApplicationAnalytics())
                .course(getCourseAnalytics())
                .gender(getGenderAnalytics())
                .build();
    }
}
