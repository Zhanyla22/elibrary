package com.example.neolabs.repository;

import com.example.neolabs.entity.operation.StudentOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentOperationRepository extends JpaRepository<StudentOperation, Long> {
}