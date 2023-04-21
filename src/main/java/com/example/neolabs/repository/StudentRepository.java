package com.example.neolabs.repository;

import com.example.neolabs.entity.Group;
import com.example.neolabs.entity.Student;
import com.example.neolabs.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);
    Page<Student> findAllByStatus(Status status, Pageable pageable);
    List<Student> findAllByStatus(Status status);// TODO: 17.04.2023 need to change its usages to the above one
    Page<Student> findAllByIsArchived(Boolean isArchived, Pageable pageable);

    @Query(nativeQuery = true,
          value = "SELECT * FROM students s WHERE " +
                  "s.id IN (SELECT sg.student_id FROM students_groups sg WHERE sg.group_id = :groupId) " +
                  "AND s.is_archived = false",
          countQuery = "SELECT * FROM students s WHERE " +
                  "s.id IN (SELECT sg.student_id FROM students_groups sg WHERE sg.group_id = :groupId) " +
                  "AND s.is_archived = false")
    Page<Student> findAllByGroupId(@Param("groupId") Long groupId, Pageable pageable);

    @Query(nativeQuery = true,
          value = "SELECT * FROM students s WHERE " +
                  "s.status = :status AND " +
                  "s.id IN (SELECT sg.student_id FROM students_groups sg WHERE sg.group_id = :groupId)",
          countQuery = "SELECT * FROM students s WHERE " +
                  "s.status = :status AND " +
                  "s.id IN (SELECT sg.student_id FROM students_groups sg WHERE sg.group_id = :groupId)")
    Page<Student> findAllByStatusAndGroupId(@Param("status") String status, @Param("groupId") Long groupId,
                                            Pageable pageable);

    @Query(nativeQuery = true,
    value = "SELECT * FROM students s WHERE " +
            "LOWER(s.first_name) LIKE LOWER(:string) OR " +
            "LOWER(s.last_name) LIKE LOWER(:string) OR " +
            "s.email LIKE :string AND " +
            "s.status = :status AND " +
            "s.is_archived = :isArch AND " +
            "s.id IN (SELECT sg.student_id FROM students_groups sg WHERE sg.group_id = :groupId)",
    countQuery = "SELECT * FROM students s WHERE " +
            "LOWER(s.first_name) LIKE LOWER(:string) OR " +
            "LOWER(s.last_name) LIKE LOWER(:string) OR " +
            "s.email LIKE :string AND " +
            "s.status = :status AND " +
            "s.is_archived = :isArch AND " +
            "s.id IN (SELECT sg.student_id FROM students_groups sg WHERE sg.group_id = :groupId)")
    List<Student> search(@Param("string") String searchString,
                         @Param("status") String status,
                         @Param("groupId") Long groupId,
                         @Param("isArch") boolean isArchived,
                         Pageable pageable);

    @Query(nativeQuery = true,
            value = "SELECT * FROM students s WHERE " +
                    "LOWER(s.first_name) LIKE LOWER(:string) OR " +
                    "LOWER(s.last_name) LIKE LOWER(:string) OR " +
                    "s.email LIKE :string AND " +
                    "s.is_archived = :isArch AND " +
                    "s.id IN (SELECT sg.student_id FROM students_groups sg WHERE sg.group_id = :groupId)",
            countQuery = "SELECT * FROM students s WHERE " +
                    "LOWER(s.first_name) LIKE LOWER(:string) OR " +
                    "LOWER(s.last_name) LIKE LOWER(:string) OR " +
                    "s.email LIKE :string AND " +
                    "s.is_archived = :isArch AND " +
                    "s.id IN (SELECT sg.student_id FROM students_groups sg WHERE sg.group_id = :groupId)"
    )
    List<Student> searchWithoutStatus(@Param("string") String searchString,
                                      @Param("groupId") Long groupId,
                                      @Param("isArch") boolean isArchived,
                                      Pageable pageable);

    @Query(nativeQuery = true,
            value = "SELECT * FROM students s WHERE " +
                    "LOWER(s.first_name) LIKE LOWER(:string) OR " +
                    "LOWER(s.last_name) LIKE LOWER(:string) OR " +
                    "s.email LIKE :string AND " +
                    "s.status = :status AND " +
                    "s.is_archived = :isArch",
            countQuery = "SELECT * FROM students s WHERE " +
                    "LOWER(s.first_name) LIKE LOWER(:string) OR " +
                    "LOWER(s.last_name) LIKE LOWER(:string) OR " +
                    "s.email LIKE :string AND " +
                    "s.status = :status AND " +
                    "s.is_archived = :isArch")
    List<Student> searchWithoutGroupId(@Param("string") String searchString,
                                       @Param("status") String status,
                                       @Param("isArch") boolean isArchived,
                                       Pageable pageable);

    @Query(nativeQuery = true,
            value = "SELECT * FROM students s WHERE " +
                    "LOWER(s.first_name) LIKE LOWER(:string) OR " +
                    "LOWER(s.last_name) LIKE LOWER(:string) OR " +
                    "s.email LIKE :string AND " +
                    "s.is_archived = :isArch",
            countQuery = "SELECT * FROM students s WHERE " +
                    "LOWER(s.first_name) LIKE LOWER(:string) OR " +
                    "LOWER(s.last_name) LIKE LOWER(:string) OR " +
                    "s.email LIKE :string AND " +
                    "s.is_archived = :isArch")
    List<Student> searchWithoutFilters(@Param("string") String searchString,
                                       @Param("isArch") boolean isArchived,
                                       Pageable pageable);
}
