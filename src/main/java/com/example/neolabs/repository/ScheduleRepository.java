package com.example.neolabs.repository;

import com.example.neolabs.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s WHERE s.group.id = ?1")
    List<Schedule> findThreeSchedulesByGroupId(Long groupId);
}
