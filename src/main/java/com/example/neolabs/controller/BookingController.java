package com.example.neolabs.controller;

import com.example.neolabs.dto.request.LoanRequest;
import com.example.neolabs.dto.response.AllLoanCountResponse;
import com.example.neolabs.dto.response.BookingInfoCountResponse;
import com.example.neolabs.entity.User;
import com.example.neolabs.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/booking")
@Tag(name = "Бронирование", description = "API Бронировние ")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/{bookId}")
    @Operation(summary = "бронь книги со стороны юзера")
    public void booking(@AuthenticationPrincipal User user,
                        @PathVariable Long bookId){
        bookingService.booking(user,bookId);
    }

    @GetMapping("/all-booked")
    @Operation(summary = "все забронированные")
    public List<BookingInfoCountResponse> getAllBooking(@RequestParam int skip,
                                                        @RequestParam int limit){
        return bookingService.getAllBookingInfo(skip,limit);
    }

    @GetMapping("/all-loan")
    @Operation(summary = "все книги которых должны вернуть")
    public List<AllLoanCountResponse> getAllLoan(@RequestParam int skip,
                                                 @RequestParam int limit){
        return bookingService.getAllLoan(skip,limit);

    }

    @DeleteMapping("/delete/{bookingId}")
    public void deleteBooking(@PathVariable Long bookingId){
        bookingService.deleteBooking(bookingId);
    }

    @PutMapping("/loan/{bookingId}")
    public void setLoanRequest(@PathVariable Long bookingId,
                               @RequestBody LoanRequest loanRequest){
        bookingService.setLoanRequest(bookingId, loanRequest);
    }

    @PutMapping("/return/{bookingId}")
    public void returnBook(@PathVariable Long bookingId){
        bookingService.returnBook(bookingId);
    }

    @GetMapping(path = "/download/info-booking")
    public void getAllBookCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=\"booking.csv\"");
        bookingService.writeCsvLoan(servletResponse.getWriter());
    }
}
