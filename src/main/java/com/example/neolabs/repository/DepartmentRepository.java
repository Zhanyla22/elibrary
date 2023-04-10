package com.example.neolabs.repository;

import com.example.neolabs.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> getDepartmentByName(String name);
}
