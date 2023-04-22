package com.example.neolabs.repository.analytics;

import com.example.neolabs.entity.analytics.ApplicationStatusHistory;
import com.example.neolabs.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationStatusHistoryRepository extends JpaRepository <ApplicationStatusHistory, Long> {
    long countByStatusBeforeArchiving(ApplicationStatus applicationStatus);
    long countByIsConverted(Boolean isConverted);
}
