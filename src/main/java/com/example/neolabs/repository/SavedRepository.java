package com.example.neolabs.repository;

import com.example.neolabs.entity.Saved;
import com.example.neolabs.entity.User;
import com.example.neolabs.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedRepository extends JpaRepository<Saved, Long> {

    List<Saved> findAllByUserAndStatus(User user, Status status);

    boolean existsByUserAndStatusAndBook_Id(User user, Status status, Long bookId);
}
