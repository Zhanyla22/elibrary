package com.example.neolabs.repository;

import com.example.neolabs.entity.Application;
import com.example.neolabs.enums.ApplicationStatus;
import com.example.neolabs.enums.Gender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    long countByGender(Gender gender);
    Page<Application> findAllByIsArchived(boolean isArchived, Pageable pageable);
    List<Application> findAllByApplicationStatusOrderByIdAsc(ApplicationStatus applicationStatus);
}
