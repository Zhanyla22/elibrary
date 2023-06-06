package com.example.neolabs.service.impl;

import com.example.neolabs.dto.request.LoanRequest;
import com.example.neolabs.dto.response.AllLoanCountResponse;
import com.example.neolabs.dto.response.BookingInfoCountResponse;
import com.example.neolabs.dto.response.BookingInfoResponse;
import com.example.neolabs.dto.response.LoanBookResponse;
import com.example.neolabs.entity.Book;
import com.example.neolabs.entity.Booking;
import com.example.neolabs.entity.User;
import com.example.neolabs.enums.BookingStatus;
import com.example.neolabs.enums.Status;
import com.example.neolabs.exception.BaseException;
import com.example.neolabs.mapper.BookingMapper;
import com.example.neolabs.repository.BookRepository;
import com.example.neolabs.repository.BookingRepository;
import com.example.neolabs.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookRepository bookRepository;

    private final BookingRepository bookingRepository;

    private static final Logger log = getLogger(CsvExportServiceImpl.class);


    @Override
    public void booking(User user, Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new BaseException("book  with id " + bookId + "not found", HttpStatus.NOT_FOUND)
        );
        bookingRepository.save(Booking.builder()
                .bookingStatus(BookingStatus.BOOKED)
                .book(book)
                .user(user)
                .build());
    }

    @Override
    public List<BookingInfoCountResponse> getAllBookingInfo(int skip, int limit) {
        List<BookingInfoResponse> bookingResponses = BookingMapper
                .entityListToDtoLists(bookingRepository.findAllByBookingStatusOrderByGetDateDesc(BookingStatus.BOOKED));
        int totalBooking = bookingRepository.findAllByBookingStatusOrderByGetDateDesc(BookingStatus.BOOKED).size();
        int startIndex = skip < totalBooking ? skip : totalBooking;
        int endIndex = Math.min(skip + limit, totalBooking);

        List<BookingInfoCountResponse> bookingInfoCountResponses = new ArrayList<>();
        BookingInfoCountResponse bookingInfoCountResponse = new BookingInfoCountResponse(bookingResponses.subList(startIndex, endIndex), totalBooking);

        bookingInfoCountResponses.add(bookingInfoCountResponse);
        return bookingInfoCountResponses;
    }

    @Override
    public List<AllLoanCountResponse> getAllLoan(int skip, int limit) {
        List<LoanBookResponse> loanBookResponses = BookingMapper.entityListToDtoListsLoan(bookingRepository.findAllByBookingStatusOrderByGetDateDesc(BookingStatus.LOAN));
        int totalBooking = bookingRepository.findAllByBookingStatusOrderByGetDateDesc(BookingStatus.LOAN).size();
        int startIndex = skip < totalBooking ? skip : totalBooking;
        int endIndex = Math.min(skip + limit, totalBooking);

        List<AllLoanCountResponse> allLoanCountResponses = new ArrayList<>();

        AllLoanCountResponse allLoanCountResponse = new AllLoanCountResponse(loanBookResponses.subList(startIndex, endIndex), totalBooking);

        allLoanCountResponses.add(allLoanCountResponse);
        return allLoanCountResponses;
    }

    @Override
    public void setLoanRequest(Long bookingId, LoanRequest loanRequest) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(
                () -> new BaseException("Not found", HttpStatus.NOT_FOUND)
        );
        booking.setGetDate(loanRequest.getGetDate());
        booking.setReturnDate(loanRequest.getPutDate());
        booking.setBookingStatus(BookingStatus.LOAN);

        bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(
                () -> new BaseException("Not found", HttpStatus.NOT_FOUND)
        );
        booking.setBookingStatus(BookingStatus.DELETED);

        bookingRepository.save(booking);
    }

    @Override
    public void returnBook(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(
                () -> new BaseException("Not found", HttpStatus.NOT_FOUND)
        );
        booking.setBookingStatus(BookingStatus.RETURNED);
        bookingRepository.save(booking);
    }

    @Override
    public void writeCsvLoan(Writer writer) {
        List<Booking> bookings = bookingRepository.findAllByBookingStatus(Status.LOAN);
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("Название", "Имя", "Фамилия", "Группа",
                    "Начало", "Конец");
            for (Booking booking : bookings) {
                csvPrinter.printRecord(booking.getBook().getTitle(), booking.getUser().getFirstName(),
                        booking.getUser().getLastName(), booking.getUser().getCourse().getName() + " " + booking.getUser().getCourse().getGroupss(),
                        booking.getGetDate(), booking.getReturnDate());
            }
        } catch (IOException e) {
            log.error("Error While writing CSV ", e);
        }
    }
}
