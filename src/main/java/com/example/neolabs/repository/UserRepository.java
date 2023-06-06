package com.example.neolabs.repository;

import com.example.neolabs.entity.User;
import com.example.neolabs.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String Email);

    boolean existsByEmail(String email);

    Optional<User> findById(Long id);

    @Query(value = "SELECT * FROM users WHERE status LIKE 'ACTIVE' AND role LIKE 'ROLE_READER'", nativeQuery = true)
    List<User> findAllByStatusAndRoleOrderByCreatedDateDesc();

    long countAllByStatus(Status status);
}
