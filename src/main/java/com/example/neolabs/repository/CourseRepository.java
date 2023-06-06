package com.example.neolabs.repository;

import com.example.neolabs.entity.Course;
import com.example.neolabs.enums.Group;
import com.example.neolabs.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Long> {

    List<Course> findAllByStatus(Status status);

    boolean existsByNameAndGroupss(String name, Group groups);

    Optional<Course> findById(Long id);
}
