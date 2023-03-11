package com.example.neolabs.repository;

import com.example.neolabs.entity.operation.ApplicationOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationOperationRepository extends JpaRepository<ApplicationOperation, Long> {
}
