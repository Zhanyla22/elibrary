package com.example.neolabs.repository;

import com.example.neolabs.entity.ResetPassword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResetPasswordRepository extends JpaRepository<ResetPassword, Long> {

    Optional<ResetPassword> findTopByCodeAndIsActiveOrderByDateExpirationDesc(String code, Boolean isActive);
}
