package com.example.neolabs.repository;

import com.example.neolabs.entity.UserOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOperationRepository extends JpaRepository<UserOperation, Long> {
}
