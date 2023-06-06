package com.example.neolabs.service.impl;

import com.example.neolabs.dto.request.AddBookRequest;
import com.example.neolabs.dto.request.UpdateBookRequest;
import com.example.neolabs.dto.response.*;
import com.example.neolabs.entity.Book;
import com.example.neolabs.entity.SpecBook;
import com.example.neolabs.entity.User;
import com.example.neolabs.enums.BookingStatus;
import com.example.neolabs.enums.Status;
import com.example.neolabs.enums.SubjectType;
import com.example.neolabs.exception.BaseException;
import com.example.neolabs.mapper.BookMainMapper;
import com.example.neolabs.repository.BookRepository;
import com.example.neolabs.repository.BookingRepository;
import com.example.neolabs.repository.SavedRepository;
import com.example.neolabs.repository.SpecBookRepository;
import com.example.neolabs.service.BookService;
import com.example.neolabs.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.apache.logging.log4j.LogManager.getLogger;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookMainMapper bookMainMapper;

    private final SpecBookRepository specBookRepository;

    private final SavedRepository savedRepository;

    private final BookingRepository bookingRepository;

    private final ResourceLoader resourceLoader;

    private final FileUploadServiceImpl fileUploadService;

    private final SubjectService subjectService;

    private static final Logger log = getLogger(CsvExportServiceImpl.class);

    @Value("${upload.dir}")
    private String uploadDir;

    @Override
    public BookInfoResponse findById(Long id, User user) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new BaseException("book  with id " + id + "not found", HttpStatus.NOT_FOUND)
        );
        BookInfoResponse bookInfoResponse = bookMainMapper.bookEntityToDto(book, user);

        return bookInfoResponse;
    }

    @Override
    public void addNewBook(AddBookRequest addBookRequest, MultipartFile pdfFile, MultipartFile imageFile) throws IOException {
        if (!bookRepository.existsByTitle(addBookRequest.getTitle())) {
            Book savedBook = bookMainMapper.bookDtoToEntity(addBookRequest);
            try {
                savedBook = bookRepository.saveAndFlush(savedBook);


                String strPdf = Objects.requireNonNull(pdfFile.getOriginalFilename()).substring(findDotIndexReverse(pdfFile.getOriginalFilename()));
                // Загрузка и сохранение PDF файла
                savedBook.setPdfLink(saveFile(pdfFile, "pdf" + savedBook.getId() + strPdf).toAbsolutePath().toString());

                // Загрузка и сохранение фото обложки
                savedBook.setUrlImage(fileUploadService.saveFile(imageFile));

                for (int i = 0; i < addBookRequest.getQuantity(); i++) {
                    SpecBook specBook = new SpecBook();
                    specBook.setBook(savedBook);
                    specBook.setStatus(Status.ACTIVE);
                    specBook = specBookRepository.saveAndFlush(specBook);
                    specBook.setInn(generateINN(specBook.getId()));
                    specBookRepository.save(specBook);
                }

            } catch (IOException e) {
                throw new RuntimeException("Ошибка при загрузке файлов", e);
            }
        }
    }

    public static int findDotIndexReverse(String str) {
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == '.') {
                return i;
            }
        }
        return -1; // Если символ '.' не найден
    }

    public Path saveFile(MultipartFile file, String fileName) throws IOException {

        // Определяем путь к файлу в корневой директории проекта
        Path filePath = Path.of(uploadDir, fileName);

        // Сохраняем файл
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return filePath;
    }

    public String generateINN(Long specBookId) {
        String id = specBookId.toString();
        StringBuilder inn = new StringBuilder();
        inn.append("0".repeat(Math.max(0, 9 - id.length())));
        return inn.append(id).toString();
    }

    @Override
    public List<BookMainResponse> findAllByStatusOrderByCreatedDateDesc() {
        List<BookMainResponse> bookMainResponses = BookMainMapper
                .entityListToDtoList(bookRepository.findAllByStatusOrderByCreatedDateDesc(Status.ACTIVE));

        return bookMainResponses;
    }

    @Override
    public EbookCountResponse countOfEbook() {
        return EbookCountResponse.builder()
                .count(bookRepository.countByBookType())
                .build();
    }

    @Override
    public List<BookResponse> getAllPopularBooks() {
        List<BookResponse> bookRespons = BookMainMapper
                .savedToPopularDto(bookRepository.findSavedEntitiesOrderByCountDesc());

        return bookRespons;
    }

    @Override
    public BookedCountResponse bookedCount() {
        return BookedCountResponse.builder()
                .bookedCount(bookingRepository.countAllByBookingStatus(BookingStatus.BOOKED))
                .leftBookCount(specBookRepository.countAll() - bookingRepository.countAllByBookingStatus(BookingStatus.BOOKED))
                .bookedPercentage(bookingRepository.countAllByBookingStatus(BookingStatus.BOOKED) * 100 / specBookRepository.countAll() + "%")
                .build();
    }

    @Override
    public BookedCountResponse loanedCount() {
        return BookedCountResponse.builder()
                .bookedCount(bookingRepository.countAllByBookingStatus(BookingStatus.LOAN))
                .leftBookCount(specBookRepository.countAll() - bookingRepository.countAllByBookingStatus(BookingStatus.LOAN))
                .bookedPercentage(bookingRepository.countAllByBookingStatus(BookingStatus.LOAN) * 100 / specBookRepository.countAll() + "%")
                .build();
    }

    @Override
    public List<BookResponse> search(String searchString) {
        List<BookResponse> bookRespons = BookMainMapper
                .savedToPopularDto(bookRepository.findAllByTitleLikeOrAuthorLike(
                        searchString,
                        searchString
                ));
        return bookRespons;
    }

    @Override
    public List<AllInfoBook> findAllByStatusOrSubjectTypeOrSubject_IdSearch(int skip,
                                                                      int limit,
                                                                      SubjectType subjectType,
                                                                      Long subjectId,
                                                                      String searchString) {
        List<BookMainResponse> bookMainResponses = BookMainMapper
                .entityListToDtoList(bookRepository.findAllByStatusOrSubjectTypeOrSubject_IdSearch(
                        searchString !=null ? searchString.toLowerCase() : "%%",
                        searchString  !=null ? searchString.toLowerCase() : "%%",
                        String.valueOf(subjectType !=null ? subjectType : "all"),
                        subjectId !=null ? subjectId : 0L));
        int totalBook = bookRepository.findAllByStatusOrSubjectTypeOrSubject_IdSearch(
                searchString,
                searchString,
                String.valueOf(subjectType),
                subjectId).size();

        int startIndex = skip < totalBook ? skip : totalBook;
        int endIndex = Math.min(skip + limit, totalBook);

        List<AllInfoBook> allInfoBooks = new ArrayList<>();

        AllInfoBook allInfoBook = new AllInfoBook(bookMainResponses.subList(startIndex, endIndex), totalBook);
        allInfoBooks.add(allInfoBook);
        return allInfoBooks;
    }


    @Override
    public void deleteBookById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new BaseException("not found", HttpStatus.NOT_FOUND)
        );
        book.setStatus(Status.DELETED);

        bookRepository.save(book);
    }

    @Override
    public void writeScvBook(Writer writer) {
        List<Book> books = bookRepository.findAllByStatus(Status.ACTIVE);
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("Название", "Автор", "Предмет", "Дата покупки",
                    "Количесвто", "Год издания", "Тип книги", "Тип Предмета", "Спонсор");
            for (Book book : books) {
                csvPrinter.printRecord(book.getTitle(), book.getAuthor(), book.getSubject().getName(), book.getYearBought(),
                        book.getQuantity(), book.getPublishedYear(), book.getBookType(), book.getSubjectType(),
                        book.getBuyer());
            }
        } catch (IOException e) {
            log.error("Error While writing CSV ", e);
        }
    }

    @Override
    public void updateBookById(Long bookId, UpdateBookRequest updateBookRequest, MultipartFile pdfFile, MultipartFile urlImage) throws IOException {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new BaseException("not found", HttpStatus.NOT_FOUND)
        );
        if (updateBookRequest.getTitle() != null) {
            book.setTitle(updateBookRequest.getTitle());
        }

        if (updateBookRequest.getAuthor() != null) {
            book.setAuthor(updateBookRequest.getAuthor());
        }

        if (updateBookRequest.getDescription() != null) {
            book.setDescription(updateBookRequest.getDescription());
        }

        //TODO: надо переделать логику : quantity переопределить
        if (updateBookRequest.getQuantity() != null) {
            book.setQuantity(updateBookRequest.getQuantity());
        }

        if (updateBookRequest.getSubjectType() != null) {
            book.setSubjectType(updateBookRequest.getSubjectType());
        }

        if (updateBookRequest.getSubject() != null) {
            book.setSubject(subjectService.findById(updateBookRequest.getSubject()));
        }

        if (updateBookRequest.getBuyer() != null) {
            book.setBuyer(updateBookRequest.getBuyer());
        }

        if (updateBookRequest.getBuyer() != null) {
            book.setBuyer(updateBookRequest.getBuyer());
        }

        if (updateBookRequest.getCost() != null) {
            book.setCost(updateBookRequest.getCost());
        }

        if (updateBookRequest.getPublishedYear() != null) {
            book.setPublishedYear(updateBookRequest.getPublishedYear());
        }

        if (updateBookRequest.getYearBought() != null) {
            book.setYearBought(updateBookRequest.getYearBought());
        }

        if (updateBookRequest.getBookType() != null) {
            book.setBookType(updateBookRequest.getBookType());
        }

        if (pdfFile != null) {
            String strPdf = Objects.requireNonNull(pdfFile.getOriginalFilename()).substring(findDotIndexReverse(pdfFile.getOriginalFilename()));
            // Загрузка и сохранение PDF файла
            book.setPdfLink(saveFile(pdfFile, "pdf" + book.getId() + strPdf).toAbsolutePath().toString());
        }

        if (urlImage != null) {
            book.setUrlImage(fileUploadService.saveFile(urlImage));
        }

        bookRepository.save(book);
    }

    @Override
    public BookInfoResponse findByOnlyId(long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new BaseException("book  with id " + bookId + "not found", HttpStatus.NOT_FOUND)
        );
        return BookInfoResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .publishedYear(book.getPublishedYear() != null ? book.getPublishedYear().toString() : null)
                .boughtYear(book.getYearBought() != null ? book.getYearBought().toString() : null)
                .buyer(book.getBuyer())
                .urlImage(book.getUrlImage())
                .pdfLink(book.getPdfLink())
                .bookType(book.getBookType())
                .bookInLibrary(specBookRepository.countSpecBookByStatusAndBook_Id(Status.ACTIVE, book.getId()) - bookingRepository.countAllByBookingStatusAndBook_Id(BookingStatus.BOOKED, book.getId()))
                .subject(subjectService.findById(book.getSubject().getId()).getName())
                .isSaved(false)
                .isAvailable(0 < bookingRepository.countAllByBookingStatusAndBook_Id(BookingStatus.BOOKED, book.getId()))
                .build();
    }
}

