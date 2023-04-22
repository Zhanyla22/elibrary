package com.example.neolabs.repository.operation;

import com.example.neolabs.entity.operation.CourseOperation;
import com.example.neolabs.entity.operation.ICourseEnrollmentCount;
import com.example.neolabs.enums.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseOperationRepository extends JpaRepository<CourseOperation, Long> {
    @Query(value = "SELECT (SELECT name FROM courses WHERE id = c.course_id) AS courseName, COUNT(c.*) AS totalEnrollments "
            + "FROM course_operations AS c WHERE c.operation_type = 'ENROLLMENT' "
            + "GROUP BY c.course_id ORDER BY COUNT(c.*) DESC", nativeQuery = true)
    List<ICourseEnrollmentCount> getTotalCoursesEnrollments();
    long countByOperationType(OperationType operationType);
}
