package com.example.neolabs.repository;

import com.example.neolabs.entity.Course;
import com.example.neolabs.entity.Mentor;
import com.example.neolabs.enums.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MentorRepository extends JpaRepository<Mentor, Long> {

    List<Mentor> findAllByCourseAndStatus(Course course, Status status);

    Boolean existsByEmail(String email);

    List<Mentor> findAllByStatus(Status statusMentor);

    Optional<Mentor> findByEmail(String email);

    @Query(value = "SELECT * FROM mentors m " +
            "WHERE " +
            "LOWER(m.first_name) LIKE LOWER(:keyword) OR " +
            "LOWER(m.last_name) LIKE LOWER(:keyword) OR " +
            "m.email LIKE :keyword AND " +
            "m.status = :status AND " +
            "m.course_id = :courseId",
            countQuery = "SELECT * FROM mentors m " +
                    "WHERE " +
                    "LOWER(m.first_name) LIKE LOWER(:keyword) OR " +
                    "LOWER(m.last_name) LIKE LOWER(:keyword) OR " +
                    "m.email LIKE :keyword AND " +
                    "m.status = :status AND " +
                    "m.course_id = :courseId",
            nativeQuery = true)
    List<Mentor> search(@Param("keyword") String keyword,
                        @Param("status") String status,
                        @Param("courseId") Long courseId,
                        Pageable pageable);


    @Query(value = "SELECT * FROM mentors m " +
            "WHERE " +
            "LOWER(m.first_name) LIKE LOWER(:keyword) OR " +
            "LOWER(m.last_name) LIKE LOWER(:keyword) OR " +
            "m.email LIKE :keyword AND " +
            "m.status = :status ", nativeQuery = true)
    List<Mentor> searchWithoutFilters(@Param("keyword") String keyword,
                                      @Param("status") String status,
                                      Pageable pageable);
}
