package com.example.neolabs.repository;

import com.example.neolabs.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findAllByIsArchived(boolean isArchived);
}
