package com.example.neolabs.repository;

import com.example.neolabs.entity.Student;
import com.example.neolabs.enums.Gender;
import com.example.neolabs.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findAllByStatus(Status status, Pageable pageable);
    List<Student> findAllByStatus(Status status);

    @Query(nativeQuery = true,
            value = "SELECT *\n" +
                    "FROM students\n" +
                    "WHERE created_date >= :date1::date\n" +
                    "AND created_date < (:date2::date + '1 day'::interval);")
    List<Student> findAllWithCreationDateMargin(@Param("date1") LocalDate date1,@Param("date2") LocalDate date2);

    long countByGender(Gender gender);
}
