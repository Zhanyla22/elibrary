package com.example.neolabs.repository;

import com.example.neolabs.entity.User;
import com.example.neolabs.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String Email);

    boolean existsByEmail(String email);

    Optional<User> findById(Long id);

    Optional<User> findUserById(Long id);

    Page<User> findAllByStatus(Status status, Pageable pageable);
}
