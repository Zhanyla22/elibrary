package com.example.neolabs.repository;

import com.example.neolabs.entity.Student;
import com.example.neolabs.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findAllByStatus(Status status, Pageable pageable);
}
