package com.example.neolabs.mapper;

import com.example.neolabs.dto.request.AddBookRequest;
import com.example.neolabs.dto.response.BookInfoResponse;
import com.example.neolabs.dto.response.BookMainResponse;
import com.example.neolabs.dto.response.BookResponse;
import com.example.neolabs.entity.Book;
import com.example.neolabs.entity.User;
import com.example.neolabs.enums.BookingStatus;
import com.example.neolabs.enums.Status;
import com.example.neolabs.repository.BookingRepository;
import com.example.neolabs.repository.SavedRepository;
import com.example.neolabs.repository.SpecBookRepository;
import com.example.neolabs.service.FileUploadService;
import com.example.neolabs.service.SubjectService;
import com.example.neolabs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookMainMapper {

    private final SubjectService subjectService;

    private final SpecBookRepository specBookRepository;

    private final FileUploadService fileUploadService;

    private final BookingRepository bookingRepository;

    private final UserService userService;

    private final SavedRepository savedRepository;


    public static BookMainResponse entityToDto(Book book) {
        return BookMainResponse.builder()
                .id(book.getId())
                .name(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .urlImage(book.getUrlImage())
                .build();
    }

    public static List<BookResponse> savedToPopularDto(List<Book> books) {
        List<BookResponse> bookRespons = new ArrayList<>();
        for (Book item : books) {
            bookRespons.add(BookResponse.builder()
                    .id(item.getId())
                    .fileUrl(item.getUrlImage()!=null ? item.getUrlImage() :null)
                    .title(item.getTitle()!=null ? item.getTitle() :null)
                    .build());
        }
        return bookRespons;
    }

    public static List<BookMainResponse> entityListToDtoList(List<Book> books) {
        return books.stream().map(BookMainMapper::entityToDto).collect(Collectors.toList());
    }

    public BookInfoResponse bookEntityToDto(Book book, User user) {
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
                .isSaved(savedRepository.existsByUserAndStatusAndBook_Id(user, Status.ACTIVE, book.getId()))
                .isAvailable(0 < bookingRepository.countAllByBookingStatusAndBook_Id(BookingStatus.BOOKED, book.getId()))
                .build();
    }

    public Book bookDtoToEntity(AddBookRequest addBookRequest) {
        return Book.builder()
                .title(addBookRequest.getTitle())
                .author(addBookRequest.getAuthor())
                .description(addBookRequest.getDescription())
                .quantity(addBookRequest.getQuantity())
                .bookType(addBookRequest.getBookType())
                .subjectType(addBookRequest.getSubjectType())
                .subject(subjectService.findById(addBookRequest.getSubject()))
                .buyer(addBookRequest.getBuyer())
                .publishedYear(addBookRequest.getPublishedYear())
                .yearBought(addBookRequest.getYearBought())
                .cost(addBookRequest.getCost())
                .status(Status.ACTIVE)
                .build();
    }


}
