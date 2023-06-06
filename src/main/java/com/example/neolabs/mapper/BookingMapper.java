package com.example.neolabs.mapper;

import com.example.neolabs.dto.response.BookingInfoResponse;
import com.example.neolabs.dto.response.LoanBookResponse;
import com.example.neolabs.entity.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookingMapper {

    public static BookingInfoResponse entityToDto(Booking booking) {
        return BookingInfoResponse.builder()
                .bookingId(booking.getId())
                .bookId(booking.getBook().getId())
                .title(booking.getBook().getTitle())
                .firstName(booking.getUser().getFirstName())
                .lastName(booking.getUser().getLastName())
                .group(booking.getUser().getCourse().getName() + " " + booking.getUser().getCourse().getGroupss())
                .build();
    }

    public static List<BookingInfoResponse> entityListToDtoLists(List<Booking> bookings) {
        return bookings.stream().map(BookingMapper::entityToDto).collect(Collectors.toList());
    }

    public static LoanBookResponse entityToDtoLoan(Booking booking) {
        return LoanBookResponse.builder()
                .bookingId(booking.getId())
                .bookId(booking.getBook().getId())
                .title(booking.getBook().getTitle())
                .firstName(booking.getUser().getFirstName())
                .lastName(booking.getUser().getLastName())
                .group(booking.getUser().getCourse().getName() + " " + booking.getUser().getCourse().getGroupss())
                .startDate(booking.getGetDate().toString())
                .endDate(booking.getReturnDate().toString())
                .build();
    }

    public static List<LoanBookResponse> entityListToDtoListsLoan(List<Booking> bookings) {
        return bookings.stream().map(BookingMapper::entityToDtoLoan).collect(Collectors.toList());
    }
}
