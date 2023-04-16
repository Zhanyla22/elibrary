package com.example.neolabs.controller;

import com.example.neolabs.service.impl.AnalyticsServiceImpl;
import com.example.neolabs.dto.response.analytics.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/analytics")
@RequiredArgsConstructor
@Tag(name = "Analytics Resource", description = "API for Analytics")
public class AnalyticsController {

    private final AnalyticsServiceImpl analyticsService;

    @GetMapping("/general")
    public ResponseEntity<GeneralAnalyticsResponse> getGeneralAnalytics(){
        return ResponseEntity.ok(analyticsService.getGeneralAnalytics());
    }

    @GetMapping("/courses")
    public ResponseEntity<CourseAnalyticsResponse> getCourseAnalytics(){
        return ResponseEntity.ok(analyticsService.getCourseAnalytics());
    }

    @GetMapping("/gender")
    public ResponseEntity<GenderAnalyticsResponse> getGenderAnalytics(){
        return ResponseEntity.ok(analyticsService.getGenderAnalytics());
    }

    @GetMapping("/applications")
    public ResponseEntity<ApplicationAnalyticsResponse> getApplicationAnalytics(){
        return ResponseEntity.ok(analyticsService.getApplicationAnalytics());
    }

    @GetMapping("/")
    public ResponseEntity<BigAnalyticsResponse> getAllAnalytics(){
        return ResponseEntity.ok(analyticsService.getAllAnalytics());
    }
}
