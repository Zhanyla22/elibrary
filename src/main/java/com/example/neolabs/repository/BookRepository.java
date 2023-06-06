package com.example.neolabs.repository;

import com.example.neolabs.entity.Book;
import com.example.neolabs.enums.Status;
import com.example.neolabs.enums.SubjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {


    Optional<Book> findById(Long id);

    boolean existsByTitle(String title);

    List<Book> findAllByStatusOrderByCreatedDateDesc(Status status);


    @Query(value = "SELECT COUNT(id) FROM books WHERE status = 'ACTIVE' and (book_type = 'E_BOOK' or book_type = 'E_PHYS_BOOK'); ", nativeQuery = true)
    Long countByBookType();

    @Query(value = "SELECT b.* FROM saved s " +
            "JOIN books b ON s.book_id = b.id " +
            "GROUP BY s.book_id, b.id " +
            "ORDER BY COUNT(s.book_id) DESC", nativeQuery = true)
    List<Book> findSavedEntitiesOrderByCountDesc();


    @Query(value = "SELECT b.* FROM books b " +
            "WHERE lower(b.title) like :title% or " +
            "      lower(b.author) like :author% ", nativeQuery = true)
    List<Book> findAllByTitleLikeOrAuthorLike(@Param("title") String title,
                                              @Param("author") String author);

    @Query(value = "SELECT b.* FROM books b " +
            "WHERE b.status = 'ACTIVE' AND (lower(b.title) like :title% or lower(b.author) like :author%) " +
            "AND (b.subject_type = :subjectType or :subjectType = 'all')" +
            "AND (b.subject_id = :subjectId or :subjectId = 0)", nativeQuery = true)
    List<Book> findAllByStatusOrSubjectTypeOrSubject_IdSearch(@Param("title") String title,
                                                        @Param("author") String author,
                                                        @Param("subjectType") String subjectType,
                                                        @Param("subjectId") Long subjectId);

    List<Book> findAllByStatus(Status status);
}
