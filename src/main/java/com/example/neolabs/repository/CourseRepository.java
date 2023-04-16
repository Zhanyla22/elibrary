package com.example.neolabs.repository;

import com.example.neolabs.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findCourseByName(String name);

    Boolean existsByName(String name);
}
