package com.example.neolabs.repository;

import com.example.neolabs.entity.Department;
import com.example.neolabs.entity.Mentor;
import com.example.neolabs.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MentorRepository extends JpaRepository<Mentor, Long> {

    List<Mentor> findAllByDepartmentNameAndStatus(String department, Status status);
}
