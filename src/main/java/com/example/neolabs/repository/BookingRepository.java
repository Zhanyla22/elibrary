package com.example.neolabs.repository;

import com.example.neolabs.entity.Booking;
import com.example.neolabs.entity.User;
import com.example.neolabs.enums.BookingStatus;
import com.example.neolabs.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByUser(User user);

    Long countAllByBookingStatus(BookingStatus bookingStatus);

    List<Booking> findAllByBookingStatusOrderByGetDateDesc(BookingStatus status);

    Long countAllByBookingStatusAndBook_Id(BookingStatus bookingStatus, Long bookId);

    List<Booking> findAllByBookingStatus(Status status);
}
