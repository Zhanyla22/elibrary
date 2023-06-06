package com.example.neolabs.repository;

import com.example.neolabs.entity.SpecBook;
import com.example.neolabs.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SpecBookRepository extends JpaRepository<SpecBook, Long> {

    Long countSpecBookByStatusAndBook_Id(Status status, Long id);

    @Query(value = "SELECT count(id) FROM spec_book WHERE status = 'ACTIVE'", nativeQuery = true)
    Long countAll();
}
