package com.example.neolabs.repository;

import com.example.neolabs.entity.Subject;
import com.example.neolabs.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject,Long> {

    List<Subject> findAllByStatus(Status status);

    Optional<Subject> findById(Long id);

    boolean existsByName(String name);
}
