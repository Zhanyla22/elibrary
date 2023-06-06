package com.example.neolabs.service;

import com.example.neolabs.dto.request.BookingRequest;
import com.example.neolabs.dto.request.LoanRequest;
import com.example.neolabs.dto.response.*;
import com.example.neolabs.entity.SpecBook;
import com.example.neolabs.entity.User;
import com.example.neolabs.enums.BookingStatus;

import java.io.Writer;
import java.util.List;

public interface BookingService {

    void booking(User user, Long bookId);

    List<BookingInfoCountResponse> getAllBookingInfo(int skip, int limit);

    List<AllLoanCountResponse> getAllLoan(int skip, int limit);

    void setLoanRequest(Long bookingId, LoanRequest loanRequest);

    void deleteBooking(Long bookingId);

    void returnBook(Long bookingId);

    void writeCsvLoan(Writer writer);
}
