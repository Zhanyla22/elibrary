package com.example.neolabs.repository.operation;

import com.example.neolabs.entity.operation.UserOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOperationRepository extends JpaRepository<UserOperation, Long> {
}
