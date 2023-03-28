package com.example.neolabs.repository;

import com.example.neolabs.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findAllByIsArchived(Boolean isArchived, Pageable pageable);
}
