package com.example.neolabs.controller;

import com.example.neolabs.controller.base.BaseController;
import com.example.neolabs.dto.request.AddBookRequest;
import com.example.neolabs.dto.request.UpdateBookRequest;
import com.example.neolabs.dto.response.*;
import com.example.neolabs.entity.User;
import com.example.neolabs.enums.SubjectType;
import com.example.neolabs.service.SpecBookService;
import com.example.neolabs.service.impl.BookServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
@Tag(name = "Книги", description = "API Книги ")
public class BookController extends BaseController {

    private final BookServiceImpl bookService;

    private final SpecBookService quantityOfPfysBooks;

    @Value("${upload.dir}")
    private String uploadDir;

    @GetMapping("/all/{skip}/{limit}")
    @Operation(summary = "Получить все книги - фильтрация - пагинация-поиск")
    public List<AllInfoBook> getAllBookByFilter(
            @PathVariable int skip,
            @PathVariable int limit,
            @RequestParam(required = false) SubjectType subjectType,
            @RequestParam(required = false) Long subjectId,
            @RequestParam(required = false) String searchString
    ) {

        return bookService.findAllByStatusOrSubjectTypeOrSubject_IdSearch(
                skip,
                limit,
                subjectType,
                subjectId,
                searchString);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Найти книгу по ID")
    public BookInfoResponse findById(@PathVariable Long id, @AuthenticationPrincipal User user) {

        return bookService.findById(id, user);
    }

    @GetMapping("/newest")
    @Operation(summary = "Новинки")
    public List<BookMainResponse> findAllNewBooks() {
        return bookService.findAllByStatusOrderByCreatedDateDesc();
    }

    @GetMapping("/ebook-count")
    @Operation(summary = "количество электронных книг")
    public EbookCountResponse countEbook() {
        return bookService.countOfEbook();
    }

    @GetMapping("/phys-book-count")
    @Operation(summary = "количество физических книг")
    public HardCoverBooksResponse countPhysBook() {
        return quantityOfPfysBooks.quantityOfPfysBooks();
    }

    //TODO:FIX
    @PostMapping(value = "/add", consumes = {"*/*"})
    public void addBook(@RequestPart(value = "addBookRequest") AddBookRequest addBookRequest,
                        @RequestPart(value = "pdfFile", required = false) MultipartFile pdfFile,
                        @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        bookService.addNewBook(addBookRequest, pdfFile, imageFile);
    }

    @GetMapping("/popular")
    @Operation(summary = "популярные книги")
    public List<BookResponse> getPopularBooks() {
        return bookService.getAllPopularBooks();
    }

    @GetMapping("/booked-count")
    @Operation(summary = "аналитика - количество забронированных книг - оставшиеся книги - и процент")
    public BookedCountResponse getCountedBooked() {
        return bookService.bookedCount();
    }

    @GetMapping("/loan-count")
    @Operation(summary = "аналитика - количество книг на руке - оставшиеся книги - и процент")
    public BookedCountResponse getCountedLoaned() {
        return bookService.loanedCount();
    }

    @GetMapping("/search")
    @Operation(summary = "Поиск книг")
    public List<BookResponse> search(@RequestParam String searchString) {

        return bookService.search(searchString.toLowerCase());
    }

    @GetMapping("/download/{bookId}")
    @Operation(summary = "Скачивание книг")
    public ResponseEntity<Resource> download(@PathVariable Long bookId) throws IOException {

        BookInfoResponse book = bookService.findByOnlyId(bookId);

        Path filePath = Paths.get(uploadDir).resolve("pdf" + book.getId() + ".pdf");
        Resource resource = new UrlResource(filePath.toUri());


        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/image-download/{bookId}")
    @Operation(summary = "Скачивание картин")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long bookId) throws IOException {

        BookInfoResponse book = bookService.findByOnlyId(bookId);

        Path filePath = Paths.get(uploadDir).resolve("image" + book.getId() + ".jpg");
        Resource photoResource = new UrlResource(filePath.toUri());


        return ResponseEntity.ok()
                .contentLength(photoResource.contentLength())
                .contentType(MediaType.IMAGE_JPEG)
                .body(photoResource);
    }

    @DeleteMapping("/delete/{bookId}")
    @Operation(summary = "удалить книгу по id")
    public void deleteBookById(@PathVariable Long bookId) {
        bookService.deleteBookById(bookId);
    }

    @GetMapping(path = "/download/info-books")
    public void getAllBookCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=\"books.csv\"");
        bookService.writeScvBook(servletResponse.getWriter());
    }

    @PutMapping(value = "/update/{bookId}", consumes = {"*/*"})

    public void updateBookById(@PathVariable Long bookId,
                               @RequestPart(value = "updateBookRequest") UpdateBookRequest updateBookRequest,
                               @RequestPart(value = "pdfFile", required = false) MultipartFile pdfFile,
                               @RequestPart(value = "urlImage", required = false) MultipartFile urlImage) throws IOException {
        bookService.updateBookById(bookId, updateBookRequest, pdfFile, urlImage);
    }
}
