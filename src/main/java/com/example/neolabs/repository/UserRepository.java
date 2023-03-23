package com.example.neolabs.repository;

import com.example.neolabs.entity.User;
import com.example.neolabs.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String Email);

    boolean existsByEmail(String email);

    Optional<User> findUserById(Long id);

    List<User> findAllByStatus(Status status);
}
