package com.example.neolabs.service;

import com.example.neolabs.dto.request.AddBookRequest;
import com.example.neolabs.dto.request.UpdateBookRequest;
import com.example.neolabs.dto.response.*;
import com.example.neolabs.entity.User;
import com.example.neolabs.enums.SubjectType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public interface BookService {

    List<AllInfoBook> findAllByStatusOrSubjectTypeOrSubject_IdSearch(int skip,
                                                                 int limit,
                                                                 SubjectType subjectType,
                                                                 Long subjectId,
                                                                 String searchString);

    BookInfoResponse findById(Long id, User user);

    void addNewBook(AddBookRequest addBookRequest, MultipartFile pdfFile, MultipartFile imageFile) throws IOException;

    List<BookMainResponse> findAllByStatusOrderByCreatedDateDesc();

    EbookCountResponse countOfEbook();

    List<BookResponse> getAllPopularBooks();

    BookedCountResponse bookedCount();

    BookedCountResponse loanedCount();

    List<BookResponse> search(String searchString);

    void deleteBookById(Long bookId);

    void writeScvBook(Writer writer);

    void updateBookById(Long bookId, UpdateBookRequest updateBookRequest, MultipartFile pdfFile, MultipartFile imageUrl) throws IOException;

    BookInfoResponse findByOnlyId(long bookId);
}
