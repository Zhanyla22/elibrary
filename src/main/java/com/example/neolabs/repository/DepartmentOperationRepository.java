package com.example.neolabs.repository;

import com.example.neolabs.entity.operation.DepartmentOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentOperationRepository extends JpaRepository<DepartmentOperation, Long> {
}
