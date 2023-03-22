package com.example.neolabs.repository;

import com.example.neolabs.entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Page<Application> findAllByIsArchived(boolean isArchived, Pageable pageable);
}
