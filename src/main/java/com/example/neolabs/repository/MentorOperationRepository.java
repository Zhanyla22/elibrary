package com.example.neolabs.repository;

import com.example.neolabs.entity.operation.MentorOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorOperationRepository extends JpaRepository<MentorOperation, Long> {
}