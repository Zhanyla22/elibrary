package com.example.neolabs.repository;

import com.example.neolabs.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query(value = "SELECT g.name FROM groups g " +
            "JOIN mentors m " +
            "ON " +
            "m.id = g.mentor_id " +
            "WHERE mentor_id =:mentorId AND g.status ='ACTIVE'", nativeQuery = true)
    List<String> findGroupsNameByMentorId(Long mentorId);
}
